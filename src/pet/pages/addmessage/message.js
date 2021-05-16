// pages/addmessage/message.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
title:"",
des:""
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },
    titleInput: function (e) {
        var pe = e.detail.value;//从页面获取到用户输入的用户名/邮箱/手机号
        if (pe != '') {
          this.setData({ title: pe });//把获取到的密码赋值给全局变量Date中的password
        }
      },
      desInput: function (e) {
        var pe = e.detail.value;//从页面获取到用户输入的用户名/邮箱/手机号
        if (pe != '') {
          this.setData({ des: pe });//把获取到的密码赋值给全局变量Date中的password
        }
      },
      sub :function(){
        wx.request({
            url: 'http://localhost:8080/add_comment',//后面详细介绍
            //定义传到后台的数据
            data: {
              //从全局变量data中获取数据
              
              title: this.data.title,
              des: this.data.des,
            },
            method: 'post',//定义传到后台接受的是post方法还是get方法
            header: {
              'content-type': 'application/x-www-form-urlencoded' // 默认值
            },
            success: function (res) {
              console.log("调用API成功");
              console.log(res)
        
              wx.redirectTo({
                url: '../admin/admin',
              })
      }})}
})