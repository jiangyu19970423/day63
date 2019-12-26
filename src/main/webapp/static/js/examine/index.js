let vm = new Vue({
    el: "#main-container",
    data: function () {
        return {
            setting: {
                data: {
                    simpleData: {
                        enable: true,//开启简单数据模式支持
                        // "pId"Key:""pId""
                        pIdKey: "parentId"
                    }
                },
                callback: {
                    onClick: this.clickNode//如果设置this.xxx  methods对象还没有，不能绑定上
                },
                view: {
                    fontCss: this.fontCss//每次对元素节点进行创建或修改的时候都会自动调用该样式设置规则
                }
            },
            name: '',
            /* appList:'',*/
            pageInfo: '',
            params: {
                type: ''
            },
            officeName: '全部'
        }
    },
    methods: {
        selectPage: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: 'manager/examine/toList',
                method: 'post',
                data: this.params
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(error => {
                layer.msg(error);
            })
        },
        initTree: function () {
            //菜单树支持两种结果的节点数组：
            // 简单数组格式 :[{"id":1,name:'个人中心',"pId":0},{"id":2,name:'单位管理',"pId":0},{"id":3,name:'业务管理',"pId":0},{"id":4,name:'我的资料',"pId":1},{"id":5,name:'单位信息',"pId":2},{"id":6,name:'单位账号',"pId":2},{"id":7,name:'电子台账',"pId":2}]
            // let  nodes= [{"id": 1, name: '个人中心', "pId": 0, open: true}, {"id": 2, name: '单位管理', "pId": 0}, {
            //     "id": 3,
            //     name: '业务管理',
            //     "pId": 0
            // }, {"id": 4, name: '我的资料', "pId": 1}, {"id": 5, name: '单位信息', "pId": 2}, {
            //     "id": 6,
            //     name: '单位账号',
            //     "pId": 2
            // }, {"id": 7, name: '电子台账', "pId": 2}]
            axios({
                url: 'manager/office/list'
            }).then(response => {
                //设置节点数据
                let nodes = response.data;
                nodes[nodes.length] = {id: 0, name: '所有机构', open: true};
                let treeObj = $.fn.zTree.init($("#pullDownTreeone"), this.setting, nodes);
            }).catch(error => {
                layer.msg(error);
            })
        },
        clickNode: function (event, treeId, treeNode) {
            this.officeName = treeNode.name;
            this.params.officeId = treeNode.id;
        },
        search: function () {
            /**
             * 1.获取树对象
             * 2.进行模糊查询匹配到所有的匹配节点数组
             * 3.获取所有节点数据，转换成简单数组模式
             * 4.遍历所有节点，给所有找到的节点设置一个高亮标记属性  清除前需要修改旧查询到的节点标记为false
             * 5.更新树对象
             */
            let treeObj = $.fn.zTree.getZTreeObj("pullDownTreeone");
            let nodes = treeObj.getNodesByParamFuzzy("name", this.name,null);
            let treeNodes = treeObj.transformToArray(treeObj.getNodes());//转换为简单数据模式
            for (let i = 0; i < treeNodes.length; i++) {
                treeNodes[i].highLight = false;
                treeObj.updateNode(treeNodes[i]);
            }

            for (let i = 0; i < treeNodes.length; i++) {
                for (let j = 0; j < nodes.length; j++) {
                    if (treeNodes[i].name == nodes[j].name) {//找到需要设置高亮的节点
                        treeNodes[i].highLight = true;
                        treeObj.updateNode(treeNodes[i]);//在树对象上更新节点
                        break;
                    }
                }
            }
        },
        fontCss: function (treeId, treeNode) {
            //如果treeNode是 单位管理，则高亮
            // return treeNode.name=="单位管理"?{color:"red"}:'';
            //如果是highLight标记为true的高亮
            return treeNode.highLight ? {color: "red"} : {color: 'black'};
        }
    },
    created: function () {
        this.selectPage(1, 5);
    },
    mounted: function () {//在挂载dom后调用
        this.initTree();
    }
});