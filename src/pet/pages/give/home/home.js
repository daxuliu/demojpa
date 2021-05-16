// pages/give/home/home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    city:"定位中.",
    parameter: [{ id: 0, name: '全部' },{ id: 1, name: '狗狗' }, { id: 2, name: '猫猫' }, { id: 3, name: '其他' }],
    list:[],
    leiid:"",
    Userentity:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    that.data.parameter[0].checked = true;
    that.setData({
      parameter: that.data.parameter,
    })//默认parameter数组的第一个对象是选中的
    wx.request({
      url: 'http://localhost:8080/adopt',
      data: {
       id:-1,
       like:0
      },
      header: {
        'Content-Type': 'application/json'
      },
      method: 'get',
      success: function (res) {
        console.log("adpot")
        console.log(res)
        that.setData({
          list: res.data
        })
        console.log("adpotlist")
        console.log(that.data.list)
      }
    })
    
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
    var that = this
    var c = that.data.city
    if (c == "定位中.") {
      console.log("进入定位")
      wx.showLoading({
        title: '加载中',
      })
      setTimeout(function () {
        wx.hideLoading()
      }, 2000)
      that.getLocation();
      console.log(that.data.leiid)
    } else {
      console.log("不是进入定位")
      wx.showLoading({
        title: '加载中',
      })
      setTimeout(function () {
        wx.hideLoading()
      }, 2000)
      
    }

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
  getLocation: function () {
    var page = this
    wx.getLocation({
      type: 'wgs84',   //默认为 wgs84 返回 gps 坐标，gcj02 返回可用于 wx.openLocation 的坐标 
      success: function (res) {
        // success  
        var longitude = res.longitude
        var latitude = res.latitude
        page.loadCity(longitude, latitude)
      }
    })
  },
  loadCity: function (longitude, latitude) {
    var page = this
    wx.request({
      url: 'https://api.map.baidu.com/geocoder/v2/?ak=T2uhrEGWSmhze1nyEHUlniIgRPW1rGPG&location=' + latitude + ',' + longitude + '&output=json',
      data: {},
      success: function (res) {
        // success  
        console.log(res);
        var city = res.data.result.addressComponent.city;
        page.setData({ city: city });
        wx.request({
          url: 'http://192.168.22.58:8771/adoption/getAdoptionList',
          data: {
            kindId: page.data.leiid,
            city: page.data.city
          },
          header: {
            'Content-Type': 'application/json'
          },
          method: 'post',
          success: function (res) {
            console.log(res)
            page.setData({
              shuju:res.data
            })
          }
        })
      },
      fail: function () {
        page.setData({ city: "获取定位失败" });
      },

    })
  },
  citybtn(){
    wx.navigateTo({
      url: '../home/city/city',
    })
  },
  parameterTap: function (e) {//e是获取e.currentTarget.dataset.id所以是必备的，跟前端的data-id获取的方式差不多
    console.log(e)
    var that = this
    var this_checked = e.currentTarget.dataset.id
    console.log(this_checked)
    var parameterList = this.data.parameter//获取Json数组
    for (var i = 0; i < parameterList.length; i++) {
      if (parameterList[i].id == this_checked) {
        parameterList[i].checked = true;//当前点击的位置为true即选中
      }
      else {
        parameterList[i].checked = false;//其他的位置为false
      }
    }
    that.setData({
      parameter: parameterList
    })
  },
  fbtn(e) {
    console.log(e.currentTarget.dataset.id)
    var id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '../home/detail/detail?id=' + id,

    })
  }
})