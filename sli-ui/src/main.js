import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import Cookies from 'js-cookie'
import 'element-ui/lib/theme-chalk/index.css';
import router from '@/router/index.js';
import store from './store'
import './permission.js';
import 'normalize.css/normalize.css' // a modern alternative to CSS resets
import '@/assets/styles/element-variables.scss'
import '@/assets/styles/index.scss' // global css
import '@/assets/icons' // icon
import Pagination from "@/components/Pagination";
import permission from './directive/permission'
import { getDicts } from "@/api/system/dict/data";
import { getConfigKey } from "@/api/system/config";
import { parseTime, resetForm, addDateRange, selectDictLabel, download, handleTree } from "@/utils/ruoyi";

Vue.config.productionTip = false
Vue.use(ElementUI, {
	size: Cookies.get('size') || 'medium' // set element-ui default size
});


Vue.prototype.msgSuccess = function (msg) {
	this.$message({showClose: true, message: msg, type: "success"})
}

Vue.prototype.msgError = function (msg) {
	this.$message({showClose: true, message: msg, type: "error"})
}

Vue.prototype.msgInfo = function (msg) {
	this.$message.info(msg)
}

// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.getConfigKey = getConfigKey
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree

// 全局组件挂载
Vue.component('Pagination', Pagination)

// 使用自定义指令
Vue.use(permission)

new Vue({
	router,
	store,
	render: h => h(App),
}).$mount('#app')


store.dispatch('GetInfo').then(res => {
	// 拉取user_info
	const roles = res.roles
	store.dispatch('GenerateRoutes', {roles}).then(accessRoutes => {
		console.log(accessRoutes)
		// 根据roles权限生成可访问的路由表
		router.addRoutes(accessRoutes) // 动态添加可访问路由表
		// next({ ...to, replace: true }) // hack方法 确保addRoutes已完成
	})
}).catch(err => {
	store.dispatch('FedLogOut').then(() => {
		// Message.error(err)
		// next({ path: '/' })
	})
})
