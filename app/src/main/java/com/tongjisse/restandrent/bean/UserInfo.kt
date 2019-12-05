package com.tongjisse.adventure.data.bean

import cn.bmob.v3.BmobObject

class UserInfo:BmobObject(){
    /**
     * 用户姓氏
     */
    var first_name:String?=null
    /**
     * 用户名字
     */
    var last_name:String?=null
    /**
     * 用户邮箱（作为唯一标示）
     */
    var email:String?=null
    /**
     * 用户密码
     */
    var password:String?=null
    /**
     * 用户手机号
     */
    var phone_num:String?=null
    /**
     * 用户年龄
     */
    var age:String?=null
}