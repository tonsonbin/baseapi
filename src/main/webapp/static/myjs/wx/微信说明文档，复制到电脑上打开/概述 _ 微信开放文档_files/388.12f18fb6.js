(window.webpackJsonp=window.webpackJsonp||[]).push([[388],{1111:function(t,a,s){"use strict";s.r(a);var n=s(1),e=Object(n.a)({},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"content"},[t._m(0),t._v(" "),s("p",[t._v("接入微信公众平台开发，开发者需要按照如下步骤完成：")]),t._v(" "),s("p",[t._v("1、填写服务器配置")]),t._v(" "),s("p",[t._v("2、验证服务器地址的有效性")]),t._v(" "),s("p",[t._v("3、依据接口文档实现业务逻辑")]),t._v(" "),s("p",[t._v("下面详细介绍这3个步骤。（如你已有小程序，并且已开通"),s("a",{attrs:{href:"https://developers.weixin.qq.com/miniprogram/dev/wxcloud/basis/getting-started.html",target:"_blank",rel:"noopener noreferrer"}},[t._v("小程序云开发"),s("OutboundLink")],1),t._v("，也可以使用 "),s("a",{attrs:{href:"https://developers.weixin.qq.com/miniprogram/dev/wxcloud/basis/web.html",target:"_blank",rel:"noopener noreferrer"}},[t._v("公众号环境共享"),s("OutboundLink")],1),t._v(" 能力，在公众号中使用云开发。）")]),t._v(" "),t._m(1),t._v(" "),s("p",[t._v("登录微信公众平台官网后，在公众平台官网的开发-基本设置页面，勾选协议成为开发者，点击“修改配置”按钮，填写服务器地址（URL）、Token和EncodingAESKey，其中URL是开发者用来接收微信消息和事件的接口URL。Token可由开发者可以任意填写，用作生成签名（该Token会和接口URL中包含的Token进行比对，从而验证安全性）。EncodingAESKey由开发者手动填写或随机生成，将用作消息体加解密密钥。")]),t._v(" "),t._m(2),t._v(" "),s("img",{attrs:{src:"https://mmbiz.qpic.cn/mmbiz/PiajxSqBRaEIQxibpLbyuSK3AXezF3wer8dofQ1JMtIBXKX9HmjE1qk3nlG0vicvB55FVL5kgsGa5RgGKRc9ug87g/0?wx_fmt=png",alt:"","data-width":"756","data-ratio":"1.0092592592592593"}}),t._v(" "),t._m(3),t._v(" "),s("p",[t._v("开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数如下表所示：")]),t._v(" "),t._m(4),s("p",[t._v("开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：")]),t._v(" "),s("p",[t._v("1）将token、timestamp、nonce三个参数进行字典序排序 2）将三个参数字符串拼接成一个字符串进行sha1加密 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信")]),t._v(" "),s("p",[t._v("检验signature的PHP示例代码：")]),t._v(" "),t._m(5),t._m(6),t._v(" "),t._m(7),t._v(" "),s("p",[t._v("验证URL有效性成功后即接入生效，成为开发者。你可以在公众平台网站中申请微信认证，认证成功后，将获得更多接口权限，满足更多业务需求。")]),t._v(" "),s("p",[t._v("成为开发者后，用户每次向公众号发送消息、或者产生自定义菜单、或产生微信支付订单等情况时，开发者填写的服务器配置URL将得到微信服务器推送过来的消息和事件，开发者可以依据自身业务逻辑进行响应，如回复消息。")]),t._v(" "),t._m(8),t._v(" "),s("p",[t._v("用户向公众号发送消息时，公众号方收到的消息发送者是一个OpenID，是使用用户微信号加密后的结果，每个用户对每个公众号有一个唯一的OpenID。")]),t._v(" "),t._m(9),t._v(" "),s("p",[t._v("另请注意，微信公众号接口必须以http://或https://开头，分别支持80端口和443端口。")])])},[function(){var t=this.$createElement,a=this._self._c||t;return a("h2",{attrs:{id:"接入概述"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#接入概述","aria-hidden":"true"}},[this._v("#")]),this._v(" 接入概述")])},function(){var t=this.$createElement,a=this._self._c||t;return a("h2",{attrs:{id:"第一步：填写服务器配置"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#第一步：填写服务器配置","aria-hidden":"true"}},[this._v("#")]),this._v(" 第一步：填写服务器配置")])},function(){var t=this.$createElement,a=this._self._c||t;return a("p",[this._v("同时，开发者可选择消息加解密方式：明文模式、兼容模式和安全模式。模式的选择与服务器配置在提交后都会立即生效，请开发者谨慎填写及选择。加解密方式的默认状态为明文模式，选择兼容模式和安全模式需要提前配置好相关加解密代码，"),a("a",{attrs:{href:"https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419318479&token=&lang=zh_CN",target:"_blank"}},[this._v("详情请参考消息体签名及加解密部分的文档")]),this._v(" 。")])},function(){var t=this.$createElement,a=this._self._c||t;return a("h2",{attrs:{id:"第二步：验证消息的确来自微信服务器"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#第二步：验证消息的确来自微信服务器","aria-hidden":"true"}},[this._v("#")]),this._v(" 第二步：验证消息的确来自微信服务器")])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"table-wrp"},[s("table",[s("thead",[s("tr",[s("th",[t._v("参数")]),t._v(" "),s("th",[t._v("描述")])])]),t._v(" "),s("tbody",[s("tr",[s("td",[t._v("signature")]),t._v(" "),s("td",[t._v("微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。")])]),t._v(" "),s("tr",[s("td",[t._v("timestamp")]),t._v(" "),s("td",[t._v("时间戳")])]),t._v(" "),s("tr",[s("td",[t._v("nonce")]),t._v(" "),s("td",[t._v("随机数")])]),t._v(" "),s("tr",[s("td",[t._v("echostr")]),t._v(" "),s("td",[t._v("随机字符串")])])])])])},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"language-php extra-class"},[s("pre",{pre:!0,attrs:{class:"language-php"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("private")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("function")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token function"}},[t._v("checkSignature")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$signature")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$_GET")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),s("span",{pre:!0,attrs:{class:"token double-quoted-string string"}},[t._v('"signature"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$timestamp")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$_GET")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),s("span",{pre:!0,attrs:{class:"token double-quoted-string string"}},[t._v('"timestamp"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$nonce")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$_GET")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),s("span",{pre:!0,attrs:{class:"token double-quoted-string string"}},[t._v('"nonce"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n\t\n    "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$token")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token constant"}},[t._v("TOKEN")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$tmpArr")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("array")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$token")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$timestamp")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$nonce")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token function"}},[t._v("sort")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$tmpArr")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token constant"}},[t._v("SORT_STRING")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$tmpStr")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token function"}},[t._v("implode")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$tmpArr")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$tmpStr")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token function"}},[t._v("sha1")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$tmpStr")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n    \n    "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("if")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$tmpStr")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("==")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token variable"}},[t._v("$signature")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("return")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token boolean constant"}},[t._v("true")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("else")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("return")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token boolean constant"}},[t._v("false")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])])},function(){var t=this.$createElement,a=this._self._c||t;return a("p",[this._v("PHP示例代码下载："),a("a",{attrs:{href:"https://res.wx.qq.com/op_res/-serEQ6xSDVIjfoOHcX78T1JAYX-pM_fghzfiNYoD8uHVd3fOeC0PC_pvlg4-kmP",target:"_blank"}},[this._v("下载")])])},function(){var t=this.$createElement,a=this._self._c||t;return a("h2",{attrs:{id:"第三步：依据接口文档实现业务逻辑"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#第三步：依据接口文档实现业务逻辑","aria-hidden":"true"}},[this._v("#")]),this._v(" 第三步：依据接口文档实现业务逻辑")])},function(){var t=this.$createElement,a=this._self._c||t;return a("p",[this._v("公众号调用各接口时，一般会获得正确的结果，具体结果可见对应接口的说明。返回错误时，可根据返回码来查询错误原因。"),a("a",{attrs:{href:"/doc/offiaccount/Getting_Started/Global_Return_Code.html"}},[this._v("全局返回码说明")])])},function(){var t=this.$createElement,a=this._self._c||t;return a("p",[this._v("此外，由于开发者经常有需在多个平台（移动应用、网站、公众帐号）之间共通用户帐号，统一帐号体系的需求，微信开放平台（"),a("a",{attrs:{href:"http://open.weixin.qq.com",target:"_blank"}},[this._v("open.weixin.qq.com")]),this._v("）提供了UnionID机制。开发者可通过OpenID来获取用户基本信息，而如果开发者拥有多个应用（移动应用、网站应用和公众帐号，公众帐号只有在被绑定到微信开放平台帐号下后，才会获取UnionID），可通过获取用户基本信息中的UnionID来区分用户的唯一性，因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号，用户的UnionID是唯一的。换句话说，同一用户，对同一个微信开放平台帐号下的不同应用，UnionID是相同的。详情请在微信开放平台的资源中心-移动应用开发-微信登录-授权关系接口调用指引-获取用户个人信息（UnionID机制）中查看。")])}],!1,null,null,null);a.default=e.exports}}]);