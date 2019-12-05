package com.tongjisse.restandrent.utils

import android.content.Context
import android.content.Intent
import com.tongjisse.restandrent.util.SharedPreferencesUtils
import com.tongjisse.restandrent.view.views.Welcome.WelcomeActivity

/**
 * SessionManager Used for quick SharedPreferences Get and Set
 * @author ZhentingYan
 */

class SessionManager(private val context: Context?) {
    //SharedPreferencesUtils Instance
    var utils = SharedPreferencesUtils.getInstance(context)
    /**
     * A set of param members with getters and setters
     * @author ZhentingYan
     */
    var isLoggedIn: Boolean = false
        get() = utils.readBoolean(IS_LOGIN, false)
        set(value) {
            utils.writeBoolean(IS_LOGIN, value)
            field = value
        }
    var email: String = ""
        get() = utils.readString(EMAIL, "")
        set(value) {
            utils.writeString(EMAIL, value)
            field = value
        }
    var firstName: String = ""
        get() = utils.readString(FIRST_NAME, "")
        set(value) {
            utils.writeString(FIRST_NAME, value)
            field = value
        }
    var lastName: String = ""
        get() = utils.readString(LAST_NAME, "")
        set(value) {
            utils.writeString(LAST_NAME, value)
            field = value
        }
    var phoneNum: String = ""
        get() = utils.readString(PHONE_NUM, "")
        set(value) {
            utils.writeString(PHONE_NUM, value)
            field = value
        }
    var district: String = ""
        get() = utils.readString(DISTRICT_NAME, "")
        set(value) {
            utils.writeString(DISTRICT_NAME, value)
            field = value
        }
    var province: String = ""
        get() = utils.readString(PROVINCE_NAME, "")
        set(value) {
            utils.writeString(PROVINCE_NAME, value)
            field = value
        }
    var city: String = ""
        get() = utils.readString(CITY_NAME, "")
        set(value) {
            utils.writeString(CITY_NAME, value)
            field = value
        }
    var defaultAddress: String = ""
        get() = this.province + " " + this.city + " " + this.district

    var longitude: String = ""
        get() = utils.readString(LONGITUDE, "")
        set(value) {
            utils.writeString(LONGITUDE, value)
            field = value
        }
    var latitude: String = ""
        get() = utils.readString(LATITUDE, "")
        set(value) {
            utils.writeString(LATITUDE, value)
            field = value
        }
    var objectId:String=""
        get()=utils.readString(OBJECT_ID,"")
        set(value){
            utils.writeString(OBJECT_ID,value)
            field=value
        }
    var age:String=""
        get()=utils.readString(AGE,"")
        set(value){
            utils.writeString(AGE,value)
            field=value
        }
    /**
     * Call this Function when user successfully log in
     * @author ZhentingYan
     */
    fun createLoginSession(objectId:String?,email: String?, firstName: String?, lastName: String?, age:String?,phoneNum: String?) {
        this.isLoggedIn = true
        this.email = email?:""
        this.firstName = firstName?:""
        this.lastName = lastName?:""
        this.phoneNum = phoneNum?:""
        this.objectId=objectId?:""
        this.age=age?:""
    }

    /**
     * Call this Function when location info has changed
     * @author ZhentingYan
     */
    fun refineLocation(province: String, city: String, district: String, longitude: String, latitude: String) {
        this.province = province
        this.city = city
        this.district = district
        this.longitude = longitude
        this.latitude = latitude
    }

    /**
     * Call this Function when user log out
     * @author ZhentingYan
     */
    fun logoutUser() {
        // Clearing all data from Shared Preferences
        utils.clearAll();
        // After logout redirect user to LogIn Activity
        val intent = Intent(context, WelcomeActivity::class.java)
        // Add new Flag to start new Activity
        // Closing all the Activities
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // Staring Login Activity
        context!!.startActivity(intent)
    }

    //Common used final Strings
    companion object {
        val EMAIL = "EMAIL"
        val OBJECT_ID="OBJECT_ID"
        val FIRST_NAME = "FIRST_NAME"
        val LAST_NAME = "LAST_NAME"
        val PHONE_NUM = "PHONE_NUM"
        val DISTRICT_NAME = "DISTRICT_NAME"
        val IS_LOGIN = "IS_LOGIN"
        val PROVINCE_NAME = "PROVINCE_NAME"
        val CITY_NAME = "CITY_NAME"
        val LATITUDE = "LATITUDE"
        val LONGITUDE = "LONGITUDE"
        val AGE="AGE"
    }
}
