let vm = new Vue({
    el: '#main-container',
    data: {
        pageInfo: '',
        statute: {
            description:''
        },//新增页面使用的对象
        params: {
            type:''
        },
        myConfig:{
            UEDITOR_HOME_URL:'static/ueditor/',  //默认ueditor加载资源的前缀路径
            charset:"utf-8",
            serverUrl:'doExec'  //  后端统一接口路径   /ueditor/doExec
        }
    },
    methods: {
        //分页查询
        selectPage: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            //发送ajax请求查询分页数据，并返回给userList接收  ，通过  vue接管遍历显示数据
            axios({
                url: "manager/statute/toList",
                method: 'post',
                data:this.params
            }).then(response => {//箭头函数可以自动将上下文的this传递到当前函数中
                this.pageInfo = response.data;

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        toUpdate: function (id) {
            axios({
                url: "manager/statute/toUpdate",
                params: {id: id}
            }).then(response => {
                layer.obj = response.data;
                layer.open({
                    type: 2,
                    title:'修改法规',
                    area: ['80%', '80%'],
                    content: 'manager/statute/toUpdatePage',
                    end: () => {
                        this.selectPage(1, 5)
                    }
                })
            }).catch(error => {
                console.log(error);
            })
        },
        insert: function () {
            //提交信息到后端
            axios({
                url: 'manager/statute/insert',
                method: 'post',
                data: this.statute
            }).then(response => {
                //返回结果如果是成功则显示提示、切换到列表页、清空新增表单信息
                if (response.data.success) {
                    this.selectPage(1, 5);
                    this.clear();
                    $("#myTab").find("li[class='active']").attr("class", '').siblings().attr("class", 'active');//切换选项卡的激活状态
                    $("#home").addClass("active");
                    $("#profile").removeClass("active");
                }
                layer.msg(response.data.msg);
            }).catch(error => {
                layer.msg(error);
            })
        },
        //清空新增表单信息
        clear: function () {
            this.statute = {}
        },
        deleteStatute: function (id) {
            axios({
                url: 'manager/statute/delete',
                params: {id: id}
            }).then(response => {
                this.selectPage(1, 5);
                layer.msg("删除成功");
            }).catch(error => {
                layer.msg(error);
            })
        }
    },
    created: function () {
        this.selectPage(1, 5)
    },
    components:{//引入vue的富文本编辑器组件
        VueUeditorWrap
    }
});