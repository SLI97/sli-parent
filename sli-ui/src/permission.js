import router from '@/router/index'
import store from '@/store/index'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
// import { Message } from 'element-ui'
import {getToken} from '@/utils/auth' // 验权

/**
 * 路由守卫-进入路由之前调用
 */
const whiteList = ['/login', '/reload'] // 不重定向白名单
router.beforeEach((to, from, next) => {
  NProgress.start()
  next()
	// store.dispatch('GenerateRoutes', {}).then(accessRoutes => {
	// 	console.log(accessRoutes)
	// 	// 测试 默认静态页面
	// 	// store.dispatch('permission/generateRoutes', { roles }).then(accessRoutes => {
	// 	// 根据roles权限生成可访问的路由表
	// 	router.addRoutes(accessRoutes) // 动态添加可访问路由表
	// 	next({ ...to, replace: true }) // hack方法 确保addRoutes已完成
	// })
  // if (getToken()) {
  //   if (whiteList.indexOf(to.path) !== -1) {
  //     next({path: '/'})
  //     NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
  //   } else {
	//     if (store.getters.roles.length === 0) {
	// 	    // 判断当前用户是否已拉取完user_info信息
	// 	    store.dispatch('GetInfo').then(res => {
	// 		    // 拉取user_info
	// 		    const roles = res.roles
	// 		    store.dispatch('GenerateRoutes', { roles }).then(accessRoutes => {
	// 			    // 测试 默认静态页面
	// 			    // store.dispatch('permission/generateRoutes', { roles }).then(accessRoutes => {
	// 			    // 根据roles权限生成可访问的路由表
	// 			    router.addRoutes(accessRoutes) // 动态添加可访问路由表
	// 			    next({ ...to, replace: true }) // hack方法 确保addRoutes已完成
	// 		    })
	// 	    })
	// 		    .catch(err => {
	// 			    store.dispatch('FedLogOut').then(() => {
	// 				    Message.error(err)
	// 				    next({ path: '/' })
	// 			    })
	// 		    })
	//     } else {
	// 	    next()
	// 	    // 没有动态改变权限的需求可直接next() 删除下方权限判断 ↓
	// 	    // if (hasPermission(store.getters.roles, to.meta.roles)) {
	// 	    //   next()
	// 	    // } else {
	// 	    //   next({ path: '/401', replace: true, query: { noGoBack: true }})
	// 	    // }
	// 	    // 可删 ↑
	//     }
  //   }
  // } else {
  //   if (whiteList.indexOf(to.path) !== -1) {
  //     next()
  //   } else {
  //     next('/login')
  //     NProgress.done()
  //   }
  // }
})

// //多个相同的路由守卫会按顺序执行，不要打乱
// router.beforeEach((to, from, next) => {
//     next()
// })

router.afterEach((to, from, next) => {
  NProgress.done() // 结束Progress
})
