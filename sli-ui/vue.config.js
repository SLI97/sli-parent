const resolve = dir => require('path').join(__dirname, dir)

module.exports = {
    publicPath: '/',//如果打包后的应用部署在服务器的根目录下，就/就可以了，否则入/app/下，就改成/app/
    lintOnSave: false,
    devServer: {
        disableHostCheck:true,
        port: 8084,
        host: '0.0.0.0',
        https: false,
        // proxy: 'http://192.168.96.160:8088',
        // {
        //     '/api': {
        //         target: 'http://192.168.96.160:8085',//目标地址
        //         ws: true, //// 是否启用websockets
        //         changeOrigin: true, //开启代理：在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
        //         pathRewrite: { '^/api': '' }    //这里重写路径
        //     }
        // }
    },
    chainWebpack: (config) => {
        config.module
            .rule('svg')
            .exclude.add(resolve('src/assets/icons'))
            .end()
        config.module
            .rule('icons')
            .test(/\.svg$/)
            .include.add(resolve('src/assets/icons'))
            .end()
            .use('svg-sprite-loader')
            .loader('svg-sprite-loader')
            .options({
                symbolId: 'icon-[name]'
            })
            .end()

        config.resolve.alias
            .set('@', resolve('./src'))
            .set('components', resolve('./src/views'))
        //set第一个参数：设置的别名，第二个参数：设置的路径
    }
}
