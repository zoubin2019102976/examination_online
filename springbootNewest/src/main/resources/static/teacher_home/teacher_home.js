function switch_active_item() { //导航栏动态切换
    var top_nav_items = document.getElementsByTagName("a");
    var i;
    for (i = 0; i < top_nav_items.length; ++i) {
        top_nav_items[i].addEventListener("click", add_class);
    }
}

function add_class() {
    var active_item = document.getElementsByClassName("active");
    if (active_item.length == 0){
        console.log("NULL");
    }else {
        active_item[0].classList.remove("active");
    }
    this.classList.add("active");
}

function logout() {
    if (confirm("您确定要退出吗?")){
        top.location = "/";
    }
    return false;
}

$(document).ready(function () { //jquery代码，发送post请求

    //处理csrf拦截
    //全局ajax函数，处理csrf拦截，为http head添加csrf头
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    //向下滑动按钮
    $("#check_paper").click(function () {
        $("#search_paper_filter").slideToggle("slow");
    });

    //登出按钮
    $("#log_out").click(function () {
        logout();
    });

    //载入试卷生成界面,需要把事件监听程序,css设置等放到这里边，因为这属于二次加载，
    // 若在外界定义事件或设置该函数内部加载的html页面，会出现无法把设置应用到该方法加载页面的元素上的情况: 比如把form表单
    // 的提交事件放到外部，则不会成功提交.
    //载入试卷生成界面并生成试卷
    $("#create_paper").click(function () {
        $("#my_content_panel").empty();
        $("#my_content_panel").load('create_page', function (responseTxt,statusTxt,xhr) {
            if(statusTxt=="success"){
                $.get("get_specialities", function (data, status) {
                    var specialties = data;
                    var selections = $("#account_specialty");
                    if (selections == null){
                        alert("error: element of select not exit.");
                        return;
                    }
                    for (var i = 0; i < specialties.length; ++i){
                        var item = $("<option></option>").text(specialties[i].specialty);
                        selections.append(item);
                    }
                });

                $(".create_paper_form_input").css("margin-top", "10px");

                $("#create_paper_form").submit(function () {//提交教师创建的试卷
                    var paperName = $("#paper_name").val();
                    var teacherName = $("#teacher_name").val();
                    var fullMarks = $("#full_marks").val();
                    var startTime = new Date($("#start_time").val());
                    var endTime = new Date($("#end_time").val());
                    var specialty = $("#account_specialty").val();

                    /*判断时间点，考试起始时间必须大于当前时间，且小于考试结束时间*/
                    var now = new Date();
                    if (startTime > endTime || startTime < new Date()){
                        alert("起始时间不能大于结束时间，或小于当前时间");
                        return false;
                    }

                    var examination_information = {
                        "specialty": specialty,
                        "paper_name": paperName,
                        "teacher": teacherName,
                        "start_time": startTime,
                        "end_time": endTime,
                        "full_marks": fullMarks
                    };

                    $.ajax(
                        {
                            url: "create_examination_paper",
                            type: "POST",
                            contentType: "application/json; charset=UTF-8",
                            dataType: "json",
                            data: JSON.stringify(examination_information),
                            success: function (data, textStatus, jqXHR) {
                                $("#paper_name").val(null);
                                $("#teacher_name").val(null);
                                $("#full_marks").val(null);
                                $("#start_time").val(null);
                                $("#end_time").val(null);
                                alert("创建试卷成功，快去编辑试卷吧!");
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                console.log("jqXHR" + jqXHR + "\n" +
                                    "textStatus: " + textStatus + "\n" +
                                    "errorThrown: " + errorThrown);
                            }
                        }
                    );

                    return false; //防止页面刷新
                });
            }
            if(statusTxt=="error")
                alert("Error this: "+xhr.status+": "+xhr.statusText);
        });
    });

    //编辑试卷
    $("#edit_paper").click(function () {
        show_examinations();
    });

    //展示所有试卷界面
    function show_examinations() {
        $("#my_content_panel").empty();
        $("#my_content_panel").load('edit_examination_paper', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success"){
                $.ajax({
                    url: "get_all_examination_paper",
                    type: "GET",
                    dataType: "json",
                    success: function (data, textStatus, jqXHR) {
                        //console.log(JSON.parse(JSON.stringify(data)).type);
                        var examination_table = $("#edit_paper_tbody");
                        var examinations =  JSON.parse(JSON.stringify(data));
                        var cnt;
                        for (cnt = 0; cnt < examinations.length; ++cnt){
                            //console.log(JSON.stringify(examinations[cnt]) + "\n");
                            var table_row = $("<tr></tr>");
                            add_table_data_to_table_row(table_row, examinations[cnt]);
                            examination_table.append(table_row);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("error status: " + textStatus + "\n" +
                            "error thrown: " + errorThrown);
                    }
                });
            }else {
                console.log("load: " + statusTxt);
            }
        });
    }

    //向表格的一行添加数据
    function add_table_data_to_table_row(table_row, examination){
        var table_head = $("<th></th>");
        table_head.attr("scope", "row");
        table_head.text(examination.id);
        table_row.append(table_head);

        var table_data = $("<td></td>");
        table_data.text(examination.paper_name);
        table_row.append(table_data);

        table_data = $("<td></td>");
        table_data.text(examination.specialty);
        table_row.append(table_data);

        var data_time = new Date(examination.start_time);
        table_data = $("<td></td>");
        table_data.text(format_date(data_time));
        table_row.append(table_data);

        data_time = new Date(examination.end_time);
        table_data = $("<td></td>");
        table_data.text(format_date(data_time));
        table_row.append(table_data);

        table_data = $("<td></td>");
        table_data.text(examination.full_marks);
        table_row.append(table_data);

        table_data = $("<td></td>");
        table_data.text(examination.teacher);
        table_row.append(table_data);

        button = $("<button></button>");
        button.text("编辑");
        button.addClass("btn btn-primary edit_paper_button_edit");
        table_data = $("<td></td>");
        table_data.append(button);
        table_row.append(table_data);

        button = $("<button></button>");
        button.text("删除");
        button.addClass("btn btn-primary btn btn-danger edit_paper_button_delete");
        table_data = $("<td></td>");
        table_data.append(button);
        table_row.append(table_data);
    }

    //格式化输出日期
    function format_date(date){
        var year = date.getFullYear();
        var month = date.getMonth();
        var day = date.getDate();
        var hour = date.getHours();
        var minutes = date.getMinutes();

        if (month < 10){
            month = "0" + month;
        }

        if (day < 10){
            day = "0" + day;
        }

        if (hour < 10){
            hour = "0" + hour;
        }

        if (minutes < 10){
            minutes = "0" + minutes;
        }

        return year + "-" + month + "-" + day +
            " " + hour + ":" + minutes;
    }

    //删除试卷并刷新展示试卷页面
    $(document).on("click", ".edit_paper_button_delete", function () {
        //alert(JSON.stringify($(this).parent().parent().contents()));
        //alert("确定要删除试卷: " + $(this).parent().parent().children().get(1).innerHTML + "?");
        if (confirm("确定要删除试卷: " + $(this).parent().parent().children().get(1).innerHTML + "?")){
            var id = $(this).parent().parent().children().get(0).innerHTML;//获取该表项的id:表项指的是这条试卷记录
            $.ajax({
                url:"delete_a_paper",
                type: "GET",
                dataType: "text",
                contentType: "application/json",
                data: {"id":id},
                success: function (data, textStatus, jqXHR) {
                    //console.log(data);
                    show_examinations();
                }
            });

            return true;
        }

        return false;
    });

    //编辑试卷html代码片段中,试卷编辑按钮事件监听器
    $(document).on("click", ".edit_paper_button_edit", function (event) {
        //alert(JSON.stringify($(this).parent().parent().contents()));
        var id = $(this).parent().parent().children().get(0).innerHTML;//获取该表项的id:表项指的是这条试卷的记录
        var paper_name = $(this).parent().parent().children().get(1).innerHTML;
        showTopicInExaminationPaper(id, paper_name);
    });

    //加载四类题型编辑的包裹界面
    function showTopicInExaminationPaper(id, paper_name) {
        $("#my_content_panel").empty();
        $("#my_content_panel").load('edit_paper_of_topic', function (responseText, statusText, xhr) {
            if (statusText == 'success'){
                //console.log("success: " + id);
                $("#edit_paper_of_topic_paper_id").text(id);
                $("#edit_paper_of_topic_paper_name").text(paper_name);
            }else {
                console.log("In showTopicInExaminationPaper(): " + statusText);
            }
        });
    }

    //绑定试题编辑界面的单选题按钮，事实证明，在on方法里不仅可以访问当前事件对象，还可以以id的形式访问该新加载的页面片段任意元素．
    $(document).on("click", "#edit_paper_of_topic_one", function (event) {
        toggle_active_in_paper_edit($(this));
        var url = "get_topic_select_one_by_paper_id";
        var button_id = "edit_paper_of_topic_one";
        edit_paper_of_topic_select(url, button_id);
    });

    //为每道单(多)选题编辑表单的编辑(删除)按钮，添加编辑(删除)事件
    //修改单选试题
    $(document).on("click", ".ensure_change_select_topic_one", function () {
        var update_url = "update_a_topic_select_one";
        var refresh_url = "get_topic_select_one_by_paper_id";
        var button_id = "edit_paper_of_topic_one"; //为了刷新单选题页面
        var update_select = get_information_of_a_topic_select_for_add($(this), button_id);
        ensure_change_select_topic(update_url, update_select, refresh_url, button_id);
    });

    //删除单选试题
    $(document).on("click", ".ensure_delete_select_topic_one", function () {
        //alert("按下删除")
        var url = "delete_a_topic_select_one";
        var refresh_url = "get_topic_select_one_by_paper_id";
        var button_id = "edit_paper_of_topic_one"; //为了刷新单选题页面
        var delete_select = get_information_of_a_topic_select_for_add($(this), button_id);
        ensure_change_select_topic(url, delete_select, refresh_url, button_id);
    });

    //绑定试题编辑界面的多选题按钮
    $(document).on("click", "#edit_paper_of_topic_many", function (event) {
        toggle_active_in_paper_edit($(this));
        var url = "get_topic_select_many_by_paper_id";
        var button_id = "edit_paper_of_topic_many";
        edit_paper_of_topic_select(url, button_id);
    });

    //修修改多选题
    $(document).on("click", ".ensure_change_select_topic_many", function () {
        var update_url = "update_a_topic_select_many";
        var refresh_url = "get_topic_select_many_by_paper_id";
        var button_id = "edit_paper_of_topic_many";
        var update_select = get_information_of_a_topic_select_for_add($(this), button_id);
        ensure_change_select_topic(update_url, update_select, refresh_url, button_id);
    })

    //删除多选试题
    $(document).on("click", ".ensure_delete_select_topic_many", function () {
        var url = "delete_a_topic_select_many";
        var refresh_url = "get_topic_select_many_by_paper_id";
        var button_id = "edit_paper_of_topic_many";
        var delete_select = get_information_of_a_topic_select_for_add($(this), button_id);
        ensure_change_select_topic(url, delete_select, refresh_url, button_id);
    });

    //插入单(多)选题试题数据事件按钮 /*通用*/
    $(document).on("click", "#create_one_select_submit", function () {
        var topic_select = get_information_for_add_a_topic_select();
        var button_id = ""; //判断是插入单选题数据还是多选题数据，配置不同button_id用于插入数据后刷新页面
        var url = ""; //判断是插入单选题数据还是多选题数据，以便向不同服务器控制器发送POST请求:单选的已经完成了，多选的还未写
        var refresh_url = "" //判断是插入单选题数据还是多选题数据，以便向不同服务器控制器发送GET请求:单选的已经完成了，多选的还未写
        if($("#edit_paper_of_topic_one").parent().hasClass("active")){
            console.log("当前是插入单选题");
            button_id = "edit_paper_of_topic_one";
            refresh_url = "get_topic_select_one_by_paper_id";
            url = "add_a_topic_select_one";
        }else if($("#edit_paper_of_topic_many").parent().hasClass("active")){
            console.log("当前是插入多选题");
            button_id = "edit_paper_of_topic_many";
            refresh_url = "get_topic_select_many_by_paper_id";
            url = "add_a_topic_select_many";
        }else {
            console.log("在判断插入单选还是多选题那里，没选到元素");
            return;
        }
        add_a_topic_select(url, topic_select, refresh_url, button_id);
    });

    //绑定试题编辑界面的判断题按钮
    $(document).on("click", "#edit_paper_of_topic_judge", function (event) {
        toggle_active_in_paper_edit($(this));
        var url = "get_topic_judge_by_paper_id";
        var button_id = "edit_paper_of_topic_judge";
        edit_paper_of_topic_judge_and_short(url, button_id);
    });

    //修改判断题
    $(document).on("click", ".ensure_change_topic_judge", function () {
        var update_url = "update_a_topic_judge";
        var refresh_url = "get_topic_judge_by_paper_id";
        var button_id = "edit_paper_of_topic_judge"; //为了刷新判断题题页面
        var update_select = get_information_of_a_topic_select_for_add($(this), button_id);
        ensure_change_judge_short_topic(update_url, update_select, refresh_url, button_id);
    });

    //删除判断题
    $(document).on("click", ".ensure_delete_topic_judge", function () {
        var update_url = "delete_a_topic_judge";
        var refresh_url = "get_topic_judge_by_paper_id";
        var button_id = "edit_paper_of_topic_judge"; //为了刷新判断题题页面
        var delete_topic = get_information_of_a_topic_select_for_add($(this), button_id);
        ensure_change_judge_short_topic(update_url, delete_topic, refresh_url, button_id);
    });

    //增加判断(简答)题
    $(document).on("click", "#create_judge_short_submit", function () {
        var topic_data = get_information_for_add_a_topic_judge_short();
        var button_id = "";
        var url = "";
        var refresh_url = "";

        if ($("#edit_paper_of_topic_judge").parent().hasClass("active")){
            console.log("当前插入的是判断题");
            button_id = "edit_paper_of_topic_judge";
            refresh_url = "get_topic_judge_by_paper_id";
            url = "add_a_topic_judge";
        }else if($("#edit_paper_of_topic_question").parent().hasClass("active")){
            console.log("当前插入的是简答题");
            button_id = "edit_paper_of_topic_question";
            refresh_url = "get_topic_short_by_paper_id";
            url = "add_a_topic_short";
        }else {
            console.log("在判断插入判断还是单选题那里，没选到元素");
            return;
        }
        add_a_topic_judge_short(url, topic_data, refresh_url, button_id);
    });


    //绑定试题编辑界面的简答题按钮
    $(document).on("click", "#edit_paper_of_topic_question", function (event) {
        toggle_active_in_paper_edit($(this));
        var url = "get_topic_short_by_paper_id";
        var button_id = "edit_paper_of_topic_question";
        edit_paper_of_topic_judge_and_short(url, button_id);
    });

    //修改简答题
    $(document).on("click", ".ensure_change_topic_short", function () {
        var update_url = "update_a_topic_short";
        var refresh_url = "get_topic_short_by_paper_id";
        var button_id = "edit_paper_of_topic_question"; //为了刷新判断题题页面
        var update_select = get_information_of_a_topic_select_for_add($(this), button_id);
        ensure_change_judge_short_topic(update_url, update_select, refresh_url, button_id);
    });

    //删除判断题
    $(document).on("click", ".ensure_delete_topic_short", function () {
        var update_url = "delete_a_topic_short";
        var refresh_url = "get_topic_short_by_paper_id";
        var button_id = "edit_paper_of_topic_question"; //为了刷新判断题题页面
        var delete_topic = get_information_of_a_topic_select_for_add($(this), button_id);
        ensure_change_judge_short_topic(update_url, delete_topic, refresh_url, button_id);
    });

    //新增(删除)判断(简答)题时，根据url,表单数据，向数据库加入一条单选题 /*复用*/
    function add_a_topic_judge_short(url, topic_data, refresh_url, button_id){
        $.ajax({
            url: url,
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(topic_data),
            success: function (data, textStatus, jqXHR) {
                alert("加入试题成功");
                edit_paper_of_topic_judge_and_short(refresh_url, button_id);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("加入试题失败: " + textStatus);
            }
        });
    }

    //新增判断(简答)题时，获取判断(简答)题通用表单数据函数 /*复用*/
    function get_information_for_add_a_topic_judge_short() {
        var paper_id = $("#edit_paper_of_topic_paper_id").text();
        var paper_name = $("#edit_paper_of_topic_paper_name").text();
        var topic_description = $("#add_judge_short_paper_topic_description").val();
        var answer = $("#add_judge_short_paper_answer").val();
        var grade_per = $("#add_judge_short_paper_grade_per").val();
        var topic_num = $("#add_judge_short_paper_topic_num").val();

        var topic_select = {
            "paper_name" : paper_name,
            "paper_id" : paper_id,
            "description" : topic_description,
            "answer" : answer,
            "grade_per" : grade_per,
            "topic_num" : topic_num
        };

        return topic_select;
    }

    //修改(删除)判断(简答)题
    function ensure_change_judge_short_topic(update_url, update_select, refresh_url, button_id) {
        $.ajax({
            url: update_url,
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(update_select),
            success: function (data, textStatus, jqXHR) {
                alert("修改成功");
                edit_paper_of_topic_judge_and_short(refresh_url, button_id);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("jqXHR" + jqXHR + "\n" +
                    "textStatus: " + textStatus + "\n" +
                    "errorThrown: " + errorThrown);
            }
        });
    }

    //当按下试题编辑界面的判断题(简答题)按钮时执行该函数,加载该id试卷下的判断题(多选题)数据,利用试卷编辑界面下的单(多)选题按钮的id区分单(多)选按钮按下/ /*复用*/
    function edit_paper_of_topic_judge_and_short(url, button_id) {
        var id = $("#edit_paper_of_topic_paper_id").text();//选择判断题所在试卷的id
        $.ajax({
            url: url,
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            data: {"id":id},
            success: function (data) {
                showTopicJudgeAndShortInTopicContentPanel(data, button_id);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("In #edit_paper_of_topic_judge click")
            }
        });
    }

    //利用试卷id加载编辑判断(简答)题界面和添加判断(简答)题界面
    function showTopicJudgeAndShortInTopicContentPanel(data, button_id) {
        var content_panel = $("#edit_paper_of_topic_content_panel");
        content_panel.empty();
        content_panel.load('get_edit_paper_of_judge_and_short_edit_form', function (responseText, statusText, xhr) {
            if (statusText == 'success'){
                fillAllTopicSelectIntoSelectOneEditForm(data, button_id);
            }
        })
    }

    //当按下试题编辑界面的单(多)选题按钮时执行该函数,加载该id试卷下的单(多)选题数据，利用试卷编辑界面下的单(多)选题按钮的id区分单(多)选按钮按下  /*复用*/
    function edit_paper_of_topic_select(url, button_id) {
        var id = $("#edit_paper_of_topic_paper_id").text(); //该选择题所属试卷的id
        $.ajax({
            //url: "get_topic_select_one_by_paper_id",
            url: url,
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            data: {"id":id},
            success: function (data) {
                showTopicSelectInTopicContentPanel(data, button_id);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("In #edit_paper_of_topic_one click: " + textStatus);
            }
        });
    }
    //利用试卷id加载编辑单选题界面和添加单选题界面,当被on方法调用时,外部函数可以通过id访问未来加载的元素 /*复用*/
    function showTopicSelectInTopicContentPanel(data, button_id){
        var content_panel = $("#edit_paper_of_topic_content_panel");
        content_panel.empty();
        content_panel.load('get_edit_paper_of_select_edit_form', function (responseText, statusText, xhr) {
            if (statusText == 'success'){
                fillAllTopicSelectIntoSelectOneEditForm(data, button_id);
            }else {
                console.log("In showTopicSelectOneInTopicContentPanel(): " + statusText);
            }
        });
    }
    //向单选择题编辑页面添加所有已加载的属于该id试卷的单(多)选题，以供修改，利用试卷编辑界面下的单选题按钮的id区分单(多)选按钮按下,
    // 为需要编辑的单(多)选题的编辑/删除按钮添加不同的类，日后在进行事件绑定时，用该类来区分单(多)选题 /*复用*/
    function fillAllTopicSelectIntoSelectOneEditForm(data, button_id){
        var edit_panel = $("#edit_paper_of_topic_content_edit");
        edit_panel.empty();
        var i;

        var change_select_class = "";
        var delete_select_class = "";

        if (button_id == "edit_paper_of_topic_one"){
            change_select_class = "ensure_change_select_topic_one";
            delete_select_class = "ensure_delete_select_topic_one";
        }else if(button_id == "edit_paper_of_topic_many") {
            change_select_class = "ensure_change_select_topic_many";
            delete_select_class = "ensure_delete_select_topic_many";
        }else if (button_id == "edit_paper_of_topic_judge"){
            change_select_class = "ensure_change_topic_judge";
            delete_select_class = "ensure_delete_topic_judge";
        }else {
            change_select_class = "ensure_change_topic_short";
            delete_select_class = "ensure_delete_topic_short";
        }

        //div 由外到内: level1-n
        //由上到下: div1-5 /*复用*/
        for(i = 0; i < data.length; ++i){
            var form_edit = $("<form style='margin-top: 15px'></form>");

            var div1 = $("<div></div>").addClass("form-row select_edit_four select_edit_one");
            var div2 = $("<div></div>").addClass("form-group select_edit_two select_edit_two");
            var div4 = $("<div></div>").addClass("form-row select_edit_four select_edit_four");
            var div5 = $("<div></div>").addClass("row justify-content-center");

            /*外层第五个div内部的两个div*/
            var div5_1 = $("<div class='col-3'></div>");
            var div5_2 = $("<div class='col-3'></div>");
            var button_change = $("<button type='button' class='btn btn-secondary btn-block'></button>").text("确认修改试题");
            var button_delete = $("<button type='button' class='btn btn-secondary btn-block'></button>").text("确认删除试题");
            button_change.addClass(change_select_class);
            button_delete.addClass(delete_select_class);
            div5_1.append(button_change);
            div5_2.append(button_delete);
            div5.append(div5_1, div5_2);
            form_edit.append(div1);

            /*外层第四个div内部的三个div*/
            var div4_1 = $("<div class='form-group col-md-4 select_edit_four_1'></div>");
            var div4_2 = $("<div class='form-group col-md-4 select_edit_four_2'></div>");
            var div4_3 = $("<div class='form-group col-md-4 select_edit_four_3'></div>");
            var label4_1_1 = $("<label></label>").text("正确答案");
            var input4_1_2 = $("<input type='text' class='form-control'></input>").val(data[i].answer);
            div4_1.append(label4_1_1, input4_1_2);
            var label4_2_1 = $("<label></label>").text("题目分值");
            var input4_2_2 = $("<input type='number' class='form-control'></input>").val(data[i].grade_per);
            div4_2.append(label4_2_1, input4_2_2);
            var label4_3_1 = $("<label></label>").text("题号");
            var input4_3_2 = $("<input type='number' class='form-control'></input>").val(data[i].topic_num);
            div4_3.append(label4_3_1, input4_3_2);
            div4.append(div4_1, div4_2, div4_3);
            form_edit.append(div2);

            if (button_id == "edit_paper_of_topic_one" || button_id == "edit_paper_of_topic_many"){
                var div3 = $("<div></div>").addClass("form-row select_edit_three select_edit_three");
                /*外层第三个div内部的四个div*/
                var div3_1 = $("<div class='form-group col-md-3 select_edit_three_1'></div>");
                var div3_2 = $("<div class='form-group col-md-3 select_edit_three_2'></div>");
                var div3_3 = $("<div class='form-group col-md-3 select_edit_three_3'></div>");
                var div3_4 = $("<div class='form-group col-md-3 select_edit_three_4'></div>");
                var label3_1_1 = $("<label></label>").text("A选项描述");
                var input3_1_2 = $("<input type='text' class='form-control'></input>").val(data[i].itemA);
                div3_1.append(label3_1_1, input3_1_2);
                var label3_2_1 = $("<label></label>").text("B选项描述");
                var input3_2_2 = $("<input type='text' class='form-control'></input>").val(data[i].itemB);
                div3_2.append(label3_2_1, input3_2_2);
                var label3_3_1 = $("<label></label>").text("C选项描述");
                var input3_3_2 = $("<input type='text' class='form-control'></input>").val(data[i].itemC);
                div3_3.append(label3_3_1, input3_3_2);
                var label3_4_1 = $("<label></label>").text("D选项描述");
                var input3_4_2 = $("<input type='text' class='form-control'></input>").val(data[i].itemD);
                div3_4.append(label3_4_1, input3_4_2);
                div3.append(div3_1, div3_2, div3_3, div3_4);
                form_edit.append(div3);
            }

            /*外层第二个div内部*/
            var label2_1 = $("<label></label>").text("题目描述");
            var input2_2 = $("<input type='text' class='form-control'></input>").val(data[i].description);
            div2.append(label2_1, input2_2);
            form_edit.append(div4);

            /*外层第一个div内部的三个div*/
            var div1_1 = $("<div></div>").addClass("form-group col-md-4 select_edit_one_1");
            var div1_2 = $("<div></div>").addClass("form-group col-md-4 select_edit_one_2");
            var div1_3 = $("<div></div>").addClass("form-group col-md-4 select_edit_one_3");
            /*外层第一个div内部的第一个div的内部*/
            var label1_1_1 = $("<label></label>").text("题目编号");
            var input1_1_2 = $("<input type='text' class='form-control' readonly></input>").val(data[i].id);
            div1_1.append(label1_1_1, input1_1_2);
            /*外层第一个div内部第二个div的内部*/
            var label1_2_1 = $("<label></label>").text("题目所属试卷名");
            var input1_2_2 = $("<input type='text' class='form-control' readonly></input>").val(data[i].paper_name);
            div1_2.append(label1_2_1, input1_2_2);
            /*外层第一个div内部第三个div的内部*/
            var label1_3_1 = $("<label></label>").text("题目所属试卷编号");
            var input1_3_2 = $("<input type='text' class='form-control' readonly></input>").val(data[i].paper_id);
            div1_3.append(label1_3_1, input1_3_2);
            div1.append(div1_1, div1_2, div1_3);
            form_edit.append(div5);

            edit_panel.append(form_edit);
        }
    }

    //获取该id试卷的单(多)选择(判断题，简答题)题信息,编辑题目时使用 /*复用*/
    function get_information_of_a_topic_select_for_add(node, button_id) {
        var id = node.parent().parent().parent().children(".select_edit_one").children(".select_edit_one_1").children(".form-control").val();
        var paper_name = node.parent().parent().parent().children(".select_edit_one").children(".select_edit_one_2").children(".form-control").val();
        var paper_id = node.parent().parent().parent().children(".select_edit_one").children(".select_edit_one_3").children(".form-control").val();

        var topic_description = node.parent().parent().parent().children(".select_edit_two").children(".form-control").val();

        var answer = node.parent().parent().parent().children(".select_edit_four").children(".select_edit_four_1").children(".form-control").val();
        var grade_per = node.parent().parent().parent().children(".select_edit_four").children(".select_edit_four_2").children(".form-control").val();
        var topic_num = node.parent().parent().parent().children(".select_edit_four").children(".select_edit_four_3").children(".form-control").val();

        if (button_id == "edit_paper_of_topic_one" || button_id == "edit_paper_of_topic_many"){
            var itemA = node.parent().parent().parent().children(".select_edit_three").children(".select_edit_three_1").children(".form-control").val();
            var itemB = node.parent().parent().parent().children(".select_edit_three").children(".select_edit_three_2").children(".form-control").val();
            var itemC = node.parent().parent().parent().children(".select_edit_three").children(".select_edit_three_3").children(".form-control").val();
            var itemD = node.parent().parent().parent().children(".select_edit_three").children(".select_edit_three_4").children(".form-control").val();

            var update_select = {
                "id" : id,
                "paper_name" : paper_name,
                "paper_id" : paper_id,
                "description" : topic_description,
                "itemA" : itemA,
                "itemB" : itemB,
                "itemC" : itemC,
                "itemD" : itemD,
                "answer" : answer,
                "grade_per" : grade_per,
                "topic_num" : topic_num
            };

            return update_select;
        }

        var update_select = {
            "id" : id,
            "paper_name" : paper_name,
            "paper_id" : paper_id,
            "description" : topic_description,
            "answer" : answer,
            "grade_per" : grade_per,
            "topic_num" : topic_num
        };

        return update_select;
    }
    //确认对单(多)选题目修改ajax代码 /*复用*/
    function ensure_change_select_topic(update_url, topic_data, refresh_url, button_id) {
        $.ajax({
            url: update_url,
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(topic_data),
            success: function (data, textStatus, jqXHR) {
                alert("修改成功");
                edit_paper_of_topic_select(refresh_url, button_id);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("jqXHR" + jqXHR + "\n" +
                    "textStatus: " + textStatus + "\n" +
                    "errorThrown: " + errorThrown);
            }
        });
    }

    //新增选择题时，获取选择题通用表单数据函数 /*复用*/
    function get_information_for_add_a_topic_select(){
        var paper_id = $("#edit_paper_of_topic_paper_id").text();
        var paper_name = $("#edit_paper_of_topic_paper_name").text();
        var topic_description = $("#add_select_one_paper_topic_description").val();
        var itemA = $("#add_select_one_paper_itemA").val();
        var itemB = $("#add_select_one_paper_itemB").val();
        var itemC = $("#add_select_one_paper_itemC").val();
        var itemD = $("#add_select_one_paper_itemD").val();
        var answer = $("#add_select_one_paper_answer").val();
        var grade_per = $("#add_select_one_paper_grade_per").val();
        var topic_num = $("#add_select_one_paper_topic_num").val();

        var topic_select = {
            "paper_name" : paper_name,
            "paper_id" : paper_id,
            "description" : topic_description,
            "itemA" : itemA,
            "itemB" : itemB,
            "itemC" : itemC,
            "itemD" : itemD,
            "answer" : answer,
            "grade_per" : grade_per,
            "topic_num" : topic_num
        };

        return topic_select;
    }

    //新增选择题，根据url,表单数据，向数据库加入一条单选题 /*复用*/
    function add_a_topic_select(url, topic_data, refresh_url, button_id){
        $.ajax({
            url: url,
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(topic_data),
            success: function (data, textStatus, jqXHR) {
                alert("加入试题成功");
                edit_paper_of_topic_select(refresh_url, button_id);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("加入试题失败: " + textStatus);
            }
        });
    }

    //试卷编辑界面下方四类题型编辑按钮切换active类 /*复用*/
    function toggle_active_in_paper_edit(node) {
        node.parent().parent().children().removeClass("active");
        node.parent().addClass("active");
    }
})

