// pages/give/home/detail/detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    swiperList: '',
    id: "",
    shuju: "",
    u:'',
    as:'',
    Userentity:"",
    adopt:null,
    tiaojian:[
      {id:1,name:'仅限同城'},
      {id:2, name: '按时打疫苗' },
      { id: 3, name: '同意适龄绝育' },
      { id: 4, name: '不得遗弃，转让，虐待' },
      { id: 5, name: '有防盗门，必须封网' },
      { id: 6, name: '接受领养前家访，领养后回访' },
      { id: 7, name: '家庭成员全员同意' },
      { id: 8, name: '工作稳定，有一定经济基础' },
    ],
    tj:[],
    user:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.id)
    this.setData({
      id: options.id
    })
    var oid = wx.getStorageSync('openid')
    var _this = this
    console.log(oid)
    wx.request({
      url: 'http://localhost:8080/adopt',
      data: {
        id: options.id,
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'post',//定义传到后台接受的是post方法还是get方法
      success: function (res) {
        console.log("调用API成功");
        console.log(res.data[0])
        var arr=res.data[0].detailed+""
        _this.setData({
          adopt: res.data[0],
          tj:arr.split(",")
        })
        
        console.log("adopt" + res.data[0])
      
        wx.request({
          url: 'http://localhost:8080/getuser',
          data: {
            id:  res.data[0].userid,
          },
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          method: 'post',//定义传到后台接受的是post方法还是get方法
          success: function (res) {
           
            _this.setData({
              user:res.data
            })
            
            console.log( res)
          },
    
          fail: function (res) {
            console.log("调用API失败");
          }
        })
        
      },

      fail: function (res) {
        console.log("调用API失败");
      }
    })
    console.log("adoptuser" )
   
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
    var that = this;
  
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
  sibtn(){
    wx.navigateTo({
      url: '../../../chat/chat?id=' + this.data.Userentity.id + "&toname=" + this.data.u.username,
    })
  },
  like(){
    var that=this
    console.log(this.data.adopt)
    wx.request({
      url: 'http://localhost:8080/like',
      data: {
       adid:  this.data.adopt.adid,
       userid:wx.getStorageSync('user').id
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'post',//定义传到后台接受的是post方法还是get方法
      success: function (res) {
       
     
        
        console.log( res)
      },

      fail: function (res) {
        console.log("调用API失败");
      }
    })
  }
})