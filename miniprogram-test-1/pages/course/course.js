// pages/course/course.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    inputShowed: false,
    inputVal: "",
    lesson: [],
    search: [],
    inStudy: [],
    canStudy: [],
    temp_Study: [],
    course_student: []
  },
  showInput: function() {
    this.setData({
      inputShowed: true
    });
  },
  hideInput: function() {
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  clearInput: function() {
    this.setData({
      inputVal: ""
    });
  },
  inputTyping: function(e) {
    this.data.temp_Study = [];
    this.setData({
      inputVal: e.detail.value
    });
    this.data.search = [];
    for (var i = 0; i < this.data.lesson.length; i++) {
      if (this.data.lesson[i].title.indexOf(this.data.inputVal) >= 0) {
        this.data.search.push(this.data.lesson[i]);
        this.data.temp_Study.push(this.data.inStudy.indexOf(this.data.lesson[i].id))
      }
    }
    console.log(this.data.temp_Study)
    this.setData({
      search: this.data.search,
      temp_Study: this.data.temp_Study
    });

  },

  addCourse(e) {
   var id = e.currentTarget.dataset.pp;
    console.log(id)
    var that = this;
    let uid = wx.getStorageSync('uid');
    var temp = {
      id:1,
      course_id:id,
      student_id:uid,
      progress:0,
      studysection:''
    }
    wx.request({
      url:'http://localhost:8080/course/addcourse',
      method:'post',
      data:temp,
      headers:{
        'Content-Type': 'application/json'
      },
      success:function(res){
        for(let i=0;i<that.data.lesson.length;i++){
          if(that.data.lesson[i].id==id){
            that.data.canStudy[i]=0;
            that.setData({
              canStudy:that.data.canStudy
            })
            console.log('ooo')
            break;
          }
        }

      }
    })


                // temp.id = that.data.Student.id;
                // temp.has_studied_section = [];
                // temp.progress = 0;
                // that.data.course_student.student_list.push(temp);
    // wx.request({
    //   url: 'http://localhost:5300/student/'+uid,
    //   headers: {
    //     'Content-Type': 'application/json'
    //   },
    //   success: function(res) {
    //     console.log(res);
    //     that.setData({
    //       Student: res.data
    //     })
    //     that.data.Student.take_course.push(id);
    //     wx.request({
    //       url: 'http://localhost:5300/student/'+uid,
    //       method: 'Put',
    //       data: that.data.Student,
    //       headers: {
    //         'Content-Type': 'application/json'
    //       },
    //       success: function(res) {
    //         wx.request({
    //           url: 'http://localhost:5300/course_student?course_id='+id,
    //           headers: {
    //             'Content-Type': 'application/json'
    //           },
    //           success: function(res) {
    //             console.log(res);
    //             that.setData({
    //               course_student: res.data[0]
    //             })
    //            
    //             wx.request({
    //               url: 'http://localhost:5300/course_student/' + that.data.course_student.id,
    //               method: 'Put',
    //               data: that.data.course_student,
    //               headers: {
    //                 'Content-Type': 'application/json'
    //               },
    //               success: function(res) {
    //                 that.setData({
    //                   inputShowed: false,
    //                   inputVal: "",
    //                   lesson: [],
    //                   search: [],
    //                   inStudy: [],
    //                   canStudy: [],
    //                   temp_Study: [],
    //                   Student: [],
    //                   course_student: []
    //                 })
    //                 that.onLoad();
    //               }
    //             })
    //           }
    //         })
    //       }
    //     })
    //   }
    // })
  },

  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    //把this对象复制到临时变量that
    var that = this;
    wx.request({
      url: 'http://localhost:8080/course/getallcourses',
      headers: {
        'Content-Type': 'application/json'
      },
      success: function(res) {
        console.log(res);
        that.setData({
          lesson: res.data
        })
        //let uid = wx.getStorageSync('userID');
        wx.request({
          url: 'http://localhost:8080/course/getcoursebyid?id='+wx.getStorageSync('uid'),
          headers: {
            'Content-Type': 'application/json'
          },
          success: function(res) {
            console.log(res.data)
            that.setData({
              inStudy: res.data
              
            })
            for (var i = 0; i < that.data.lesson.length; i++) {
              let sum=0;
              for(let j=0;j<that.data.inStudy.length;j++){
              if (that.data.inStudy[j]==that.data.lesson[i].id) {
                sum++
                break;
              }
              }
              if(sum>0)that.data.canStudy.push(0)
              else that.data.canStudy.push(1)
            }
            
            that.setData({
              canStudy: that.data.canStudy
            });
            console.log(that.data.canStudy);
           // console.log(that.data.inStudy.indexOf('ddd'));
          }
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})