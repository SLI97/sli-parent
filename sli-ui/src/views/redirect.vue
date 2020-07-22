<!--<template>-->
<!--</template>-->
<script>
	export default {
		created() {
			const { params, query } = this.$route
			const { path } = params
			this.$router.replace({ path: '/' + path, query })
		},
		render: function(h) {  //不写模板会报错，自己手动写一个空模板或者主动调用render函数
			return h() // avoid warning message
		}
	}
</script>
