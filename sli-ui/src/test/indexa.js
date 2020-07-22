// const obj = require("./indexb.js")  //obj为{a : 1,b : 2,c:3}

// exports.d = 4    //忽略
// exports.f = 6    //忽略
// module.exports.g = 7      // obj为{a : 1,b : 2,c:3，g:7}
// module.exports = obj      // obj为{a : 1,b : 2,c:3}
// module.exports.h = 8      // obj为{a : 1,b : 2,c:3,h:8}

//总结:module,exports暴露一个对象，module.exports完美覆盖exports，剩下逻辑跟对象引用完全一样

//---------------------------------------------------

// import obj from './indexb'

// export const obj2 = obj

// export const obj1 = {
//     b:2
// }

// export function asd(){

// }

//总结:export跨域暴露多个 import用花括号,export default 暴露单个，import时就是import一个对象