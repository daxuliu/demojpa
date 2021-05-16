Page({
  /**
   * 页面的初始数据
   */
  data: {
    swiperList: [{
      id: 0,
      type: 'image',
      url: '/pages/images/1.jpg'
    }, {
      id: 1,
      type: 'image',
      url: '/pages/images/2.jpg'
    },
    {
      id: 2,
      type: 'image',
      url: '/pages/images/3.jpg'
    }
    ],
    iconList: [{
      id: 1,
      icon: 'new',
      color: 'red',
      name: '助养'
    }, {
      id: 3,
      icon: 'github',
      color: 'yellow',
      name: '领养'
    }, ],
    gridCol: 4,
    skin: false,
    ad:null
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this
    wx.request({
      url: 'http://localhost:8080/radopt',//后面详细介绍
      //定义传到后台的数据
      data: {
      },
      method: 'post',//定义传到后台接受的是post方法还是get方法
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      success: function (res) {
        console.log("调用API成功");
       
      that.setData({
        ad:res.data
      })
      console.log(that.data.ad) 
        
      },
      fail: function (res) {
        console.log("调用API失败");
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
   
   var user=wx.getStorageSync('user');
   console.log(user)
   
   if(user==undefined||user==""||user==none){//是否登录
    console.log("to login")
    wx.redirectTo({
       url: '../../login/login',
     })
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

  swiperclick: function (e) {
    console.log(e.currentTarget.dataset.id);

    var id = JSON.stringify(e.currentTarget.dataset.id);
    console.log(id);
      wx.navigateTo({
        url: '../goods/goods?id=' + id,
      })
  },

  cuclick:function(e){
    console.log(e.currentTarget.dataset.id);

    var id = JSON.stringify(e.currentTarget.dataset.id);
    console.log(id);
    if(id=='1'){
      wx.navigateTo({
        url: '../../find/home/home',
      })
    }else if(id=='3'){
      wx.navigateTo({
        url: '../../give/home/home',
      })
    }
  },
  heclick: function () {
    wx.navigateTo({
      url: '../logs/logs',
    })
  },
  xian : function(){
    wx.switchTab({
      url: '../../plugin/home/home',
    })
  }

})
