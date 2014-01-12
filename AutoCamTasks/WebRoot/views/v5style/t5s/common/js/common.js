/* 全站通用的js代码可以暂时放在这里 */
(function(){
    /* 展开分类菜单的位置计算,避免菜单超过第二屏*/
    var closeCateCon = $('.category-status-close .category-content');
    closeCateCon.show(); // 父级隐藏的获取不到子集的高度,so先显示计算完毕后隐藏
    $('.category-item-two').each(function(){
        var self = $(this);
        var h = self.height();
        var parent = self.closest('.category-item');
        // top的最大高度
        var parentH = parent.height();
        var pTop = parent.position().top;
        if(h > pTop + parentH){
            if(pTop == 0){
                self.css('top',-pTop-1);
            }else{
                self.css('top',-pTop+1);
            }
        }else if(h > (pTop/2)){
            self.css('top',-h/2);
        }
    });
    closeCateCon.attr('style','');

    // 分类菜单延迟加载
    var timer1,timer2;
    $('.category').delegate('.category-item','mouseover',function(){
        var _this = $(this);
        clearTimeout(timer1);
        timer1 = setTimeout(function(){
            $('.category-content').find('.hover').removeClass('hover');
            _this.addClass('hover');
        },150);
    })
    $('.category').delegate('.category-content','mouseleave',function(){
        var _this = $(this);
        clearTimeout(timer1);
        setTimeout(function(){
            _this.find('.hover').removeClass('hover');
        },200)
    })

    $('.category-status-close').bind('mouseover',function(){
        var _this = $(this);
        timer2 = setTimeout(function(){
            _this.find('.category-content').show();
        },300)
    })
    $('.category-status-close').bind('mouseleave',function(){
        var _this = $(this);
        clearTimeout(timer2);
        setTimeout(function(){
            _this.find('.category-content').hide();
        },300)
    });
    if($('.hall-nav').length>0) {// 设置需求市场相关header背景色
    	$('#j-zbj-header ,.hall-nav,.ui-logo-sub').css('background-color',"#f6f6f6");
    }
})();

;(function(){
    // t5中的此处控制由这里一起处理.
//    if( $(document.body).hasClass('t5s') ){
//        return;
//    }
    var msgRequestURI = 'http://u.' + ZBJInfo.baseURI + '/notice/getcount?jsonpcallback=?';
    var msgHandle = {
        requestTime: 200,
        intervalTime: 30 * 60 * 1000,
        init: function(){
            if ( /https/.test(window.location.href) ) {
                return false;
            }
            if ( !ZDK.cookie.getCookie('userid') ) {
                return;
            }
            this.start();
            this.bindEvent();
        },
        start: function(){
            var self = this;
            setTimeout(function(){
                self.sendRequest();
            }, self.requestTime);
        },
        sendRequest: function(){
            var self = this;
            $.getJSON(msgRequestURI, function(json){
                if (json) {
                    self.updateMsgIndicator(json);
                }
            });
        },
        updateMsgIndicator: function( data ) {
            var totalMsg = 0;
            for ( var p in data ) {
                if ( data.hasOwnProperty(p) ) {
                    var msgNum = data[p]*1;
                    var msgItem = $('#j-msg-' + p);
                    // 后台返回的数据中居然有负值!!!
                    if (msgNum > 0) {
                        totalMsg += msgNum;
                    }

                    if ( msgNum > 0 ) {
                        msgItem.find('.highlight').remove();
                        msgItem.prepend(' <span class="highlight">' + msgNum + '</span>');
                        if (p=='chengxin') {
                        	msgItem.parent('li').show();
                        }
                    } else {
                        msgItem.find('span').remove();
                        if (p=='chengxin') {
                            msgItem.parent('li').hide();
                        }
                    }

                }
            }
            this.setBlinkIcon(totalMsg > 0);
            this.requestTime = this.intervalTime;
            this.start();
        },
        setBlinkIcon: function (blink){
            if (blink) {
                if (!this._blinkTimer) {
                    this._blinkTimer = setInterval(function (){
                        $('#j-msg-icon').css('visibility', 'hidden');
                        setTimeout(function (){
                            $('#j-msg-icon').css('visibility', 'visible');
                        }, 400);
                    }, 1000);
                    $('#j-msg-tip').show();
                    var self = this;
                    $('.navusernews').one('mouseover', function () {
                        $('#j-msg-tip').hide();
                        self.setBlinkIcon(false);
                    });
                }
            } else {
                if (this._blinkTimer) {
                    clearInterval(this._blinkTimer);
                    $('#j-msg-icon').css('visibility', 'visible');
                    this._blinkTimer = null;
                }
            }
        },
        bindEvent: function(){
            var self = this;
            $('ul.item-usernews-dropdown a').click(function(){
                self.onmsgItemClick( $(this) );
            });
        },
        onmsgItemClick: function( obj ){
            var msgNumObj = obj.find('span');
            if ( msgNumObj.size() ) {
                var leftMsgNum = parseInt($('#j-msg-total').html(),10) - parseInt(msgNumObj.html().replace(/\s/g,''),10);
                if ( leftMsgNum > 0 ) {
                    $('#j-msg-total').html( leftMsgNum).show();
                } else {
                    $('#j-msg-total').hide();
                }
                msgNumObj.remove();
            }
        },
        makeTips: function(){

        }

    };
    msgHandle.init();


    //实时系统消息推送
    if(!/login.v5.zbj.com/.test(window.location.href) && !/login.zhubajie.com/.test(window.location.href)){
        if(window.WebIM && WebIM.onNotification){
            WebIM.onNotification(function(notification){
                var json = notification.content.msgcount;
                msgHandle.updateMsgIndicator( json );
            });
        }
    }
})();


/*页面右侧的返回顶部以及意见反馈等等*/
;(function(){
    if ( document.location.href.indexOf('https://') >= 0 ) {
        return;
    }

    var formatStr = function( format, args ){
        return format.replace(/{%([^%]*)%}/g, function( str, match ){
            return args[match];
        });
    };

    function generateChatDom(){
        var defaultCfg = {
            'title': '在线咨询',
            'link': 'http://livechat.zhubajie.com/LR/Chatpre.aspx?id=PCF83427900&skid=27',
            'className': ''
        };
        if ( window.ZBJInfo && window.ZBJInfo.chatCfg ) {
            $.extend( defaultCfg, window.ZBJInfo.chatCfg );
        }
        var chatHTML = '<div class="ui-tools-top ui-tools-livechat">' +
            '<a class="{%className%}" href="{%link%}" target="_blank" title="{%className%}">' +
            '<i class="ui-ico ui-ico-cs"></i>' +
            '</a>' +
            '</div>';

        chatHTML = formatStr( chatHTML, defaultCfg );
        return $(chatHTML).appendTo(document.body);
    }

    function generateFeedbackHTML(){
        var defaultCfg = {
            'title': '意见反馈',
            'link': 'http://task.zhubajie.com/3105598/',
            'className': ''
        };
        if ( window.ZBJInfo && window.ZBJInfo.feedbackCfg ) {
            $.extend( defaultCfg, window.ZBJInfo.feedbackCfg );
        }
        var feedbackHTML = '<a href="{%link%}" target="_blank" class="item-tools {%className%}" id="j-feedback-btn">' +
            '<span class="item-ico"><i class="ui-ico ui-ico-feed"></i></span>' +
            '<span class="item-txt">{%title%}</span>' +
            '</a>';

        feedbackHTML = formatStr( feedbackHTML, defaultCfg );
        return feedbackHTML;
    }

    var rightToolBtm = '<div class="ui-tools-bottom">' +
                            '<div class="ui-tools-gotop">' +
                                '<a class="item-tools" href="javascript:;" title="返回顶部" id="j-goTop"><i class="iconfont">&#xe806;</i></a>' +
                                '<i class="ui-ico ui-ico-toppig"></i>' +
                            '</div>' +
                            '<div class="ui-tools-app">' +
                                '<a class="item-tools" href="http://m.zhubajie.com" target="_blank">' +
                                    '<span class="item-ico"><i class="ui-ico ui-ico-mobile"></i></span>' +
                                    '<span class="item-txt">移动应用</span>' +
                                    '<div class="ui-poptipnoc ui-poptipnoc-left">' +
                                        '<div class="ui-poptipnoc-arrow"><i></i></div>' +
                                        '<div class="ui-poptipnoc-bd">' +
                                            '<div>' +
                                                '<div class="item-weixin"></div>' +
                                                '<p>官方微信<br/>轻松找人做事</p>' +
                                            '</div>' +
                                            '<div class="ui-vline"></div>' +
                                            '<div>' +
                                                '<div class="item-mobile"></div>' +
                                                '<p style="line-height: 32px;">手机下单，送红包</p>' +
                                            '</div>' +
                                        '</div>' +
                                    '</div>' +
                                '</a>' +
                            '</div>' +
                            '<div class="ui-tools-feed">' + generateFeedbackHTML() +
                            '</div>' +
                        '</div>';

    // 不考虑IE6
    $(function(){
        var $body = $('body');

        if ( window.ZBJInfo && window.ZBJInfo.isShowChatEntry ) {
            generateChatDom();
        }
        var $rightToolBtm = $(rightToolBtm).appendTo($body);
        if (window.fix_poptip_on_t5) {
            fix_poptip_on_t5($rightToolBtm);
        }
        var $goTop = $rightToolBtm.find('.ui-tools-gotop').hide();
        var goTopPig = $goTop.find(".ui-ico-toppig").hide();
        var goTopIco = $goTop.find(".item-tools");
        function getScrollTop(){
            return $(window).scrollTop();
        }
        function setScrollTop(value){
            $(window).scrollTop(~~value);
        }
        $(window).scroll(function(){
            if(getScrollTop()>0){
                $goTop.show();
            }else{
                $goTop.hide();
            }
        });
        $rightToolBtm.delegate(".ui-tools-gotop",'click',function(){
            goTopIco.hide();
            goTopPig.show();
            var timer=setInterval(scrollMove,5);
            function scrollMove(){
                setScrollTop(getScrollTop()/1.1);
                if(getScrollTop()<1){
                    clearInterval(timer);
                    goTopPig.hide();
                    goTopIco.show();
                }
            }
        });
    });


    if (!window["mobileVerify"]) {
        //手机绑定
        var alertText = location.href == "http://u.zhubajie.com/pay/withdraw" ? "您尚未绑定手机，提现操作可能存在一定风险，我们建议您立即绑定手机。" : "你的账户中有余额，为了保障你的资金安全，建议你立即绑定手机。";

        var html1 = $('<form id="cert-phone">'
            + '<div class="gray9 mt10 alert"><div class="alert-img"><em></em>' + alertText + '</div></div>'
            + '<div class="control-group clearfix mt15">'
            + '<label class="control-label" for="inputWarning" style="width:110px">输入你的手机号码</label>'
            + '<div class="controls">'
            + '<em>请输入手机号码</em>'
            + '<p>'
            + '<input type="text" id="inputWarning">'
            + '<span class="help-inline">暂只支持中国地区手机号码</span>'
            + '</p>'
            + '</div>'
            + '</div>'
            + '</form>');
        var html2 = $('<style>.ui-verify-tips .ui-poptip-arrow{left:30px}</style>'
            + '<form id="phone-verify">'
            + '<div class="alert">'
            + '<div class="alert-img"><em></em>已发送短信验证码至<strong class="orange phone-number">136 **** 9856</strong><a href="" class="ml10 underline" style="color:#999">错误的手机号？</a></div>'
            + '</div>'
            + '<div class="control-group clearfix mt15">'
            + '<label class="control-label" style="width:110px;padding-top:6px">请输入短信验证码</label>'
            + '<div class="controls">'
            + '<em style="top:5px">请输入短信验证码</em>'
            + '<input type="text" style="padding:5px" name="code"><a class="butn ml10 butn-green" href="###" id="resendcode"><i></i>重发验证码</a>'
            + ' </div>'
            + '</div>'
            + '<input type="hidden" name="vid" />'
            + '</form>');
        var sendUrl = "http://u." + ZBJInfo.baseURI + "/cert/sendcode",
            resendUrl = "http://u." + ZBJInfo.baseURI + "/cert/resendcode",
            verifyUrl = "http://u." + ZBJInfo.baseURI + "/cert/vmobile";
        safepwdUrl = "http://u." + ZBJInfo.baseURI + "/cert/checksafepass";
        //包含这些链接时不验证手机
        var completeUrl = "login." + ZBJInfo.baseURI + "/register/complete",
            tagUrl = "login." + ZBJInfo.baseURI + "/register/tag",
            peopleUrl = "login." + ZBJInfo.baseURI + "/register/people",
            payUrl = "pay/withdraw";

        var get_cookie = (ZDK.cookie && ZDK.cookie.getCookie) ? ZDK.cookie.getCookie : GetCookie;
        var set_cookie = (ZDK.cookie && ZDK.cookie.setCookie) ? ZDK.cookie.setCookie : SetCookie;

        //GetCookie("needmobile")==1 &&
        if ((get_cookie("needmobile") == 1 && /cert\/mobile/.test(location.href) == false ) || (/pay\/withdraw/.test(location.href) == true && get_cookie("mobilepoped"))) {
            //if(location.href.indexOf(completeUrl) && location.href.indexOf(tagUrl) && location.href.indexOf(peopleUrl)){
            if ($("#noMobileTip").size() == 0) { // 服务商主页需要弹出服务商入驻的弹窗，需禁止验证弹窗
                mobileVerify();
            }
            //}
        };
        function mobileVerify(fuName, options) {
            $.ajax({
                url: safepwdUrl,
                data: "tips=1",
                dataType: "jsonp",
                jsonp: "jsonpcallback",
                success: function (json) {
                    var typeTxt;
                    if (json.state == -2) {
                        typeTxt = "输入你的安全密码";
                        html1.removeAttr("id").addClass("safepwdUrl");
                        html1.find(".control-group").html('<label style="width:110px" for="inputWarning" class="control-label">输入你的安全密码</label><div class="controls"><em>输入你的安全密码</em><p><input type="password" id="inputWarning"><a target="_blank" href="http://help./main/findspas" class="ml10 underline">忘记密码？</a></p></div>');
                        bindMobile(fuName, options);
                        html1.find(".controls").inputEmpty(typeTxt);
                    } else {
                        typeTxt = "请输入手机号码";
                        bindMobile(fuName, options);
                    }
                }
            });
        }

        function bindMobile(fuName, options) {
            var btn_cancel = options && options.btn_cancel;
            var win = ZDK.module.window({
                title: "账户安全提醒",
                content: html1.show(),
                width: 485,
                mask: true,
                cache: false,
                zIndex: 500,
                allowClose: true,
                hasIframe: true,
                ok: "下一步",
                cancel: btn_cancel || "跳过，下次再说"
            });
            if (options) {
                options.alert && html1.find(".alert-img").html('<em></em>' + options.alert);
                options.title && win.resetTitle(options.title);
            }
            html1.find(".controls").inputEmpty("请输入手机号码");
            //关闭弹窗时清除cookie//
            win.Close.click(function () {
                fuName && fuName();
                //DeleteCookie("nextopen");
            });
            //回车提交
            win.Body.find("form").submit(function () {
                win.Footer.find(".ui-window-ok").click();
                return false;
            });
            win.on("onok", function () {
                if ($("#cert-phone").length) {
                    changeTips(win);
                } else if ($(".safepwdUrl").length) {
                    $.ajax({
                        url: safepwdUrl,
                        data: "s_pas=" + $(".safepwdUrl").find("input[type='password']").val() + "&act=1",
                        dataType: "jsonp",
                        jsonp: "jsonpcallback",
                        success: function (json) {
                            if (json.state == 1) {
                                html1.attr("id", "cert-phone").removeClass("safepwdUrl");
                                html1.find(".control-group").html('<label class="control-label" for="inputWarning" style="width:110px">输入你的手机号码</label><div class="controls"><em>请输入手机号码</em><p><input type="text" id="inputWarning"><span class="help-inline">暂只支持中国地区手机号码</span></p></div>');
                                html1.find(".alert").hide();
                                win.resetBody(html1.show());
                                html1.find(".controls").inputEmpty("请输入手机号码");
                            } else {
                                ZDK.Tips(json.msg, 2000, "error");
                            }
                        }
                    });
                } else {
                    $.ajax({
                        url: verifyUrl,
                        data: "vid=" + html2.find("input[name=vid]").val() + "&code=" + html2.find("input[name=code]").val(),
                        dataType: "jsonp",
                        jsonp: "jsonpcallback",
                        success: function (json) {
                            if (json.state == 1) {
                                //DeleteCookie("needmobile");
                                ZDK.Tips("您的手机已绑定成功！", 2000, "success");
                                //SetCookie("needmobile",null);
                                win.hide();
                                if (fuName) {
                                    fuName();
                                    return false;
                                } else if (/payorder|task/.test(location.href)) {
                                    setTimeout(function () {
                                        location.reload();
                                    }, 2000);
                                }
                            } else {
                                ZDK.Tips(json.msg, 2000, "error");
                            }
                        }
                    });
                }
                return false;
            });
            win.on("oncancel", function () {
                var url, type;
                if (/u.zhubajie.com/.test(location.href)) {
                    url = "http://u." + ZBJInfo.baseURI + "/main/disablepop";
                    type = "get";
                } else {
                    url = "http://u." + ZBJInfo.baseURI + "/main/disablepop?jsonpcallback=?";
                    type = "jsonp";
                }

                $.ajax({
                    type: type,
                    url: url,
                    dataType: "json",
                    complete: function () {
                    }})
                // 每天只提醒一次
                set_cookie('needmobile',0,getOneDaysLater());
                function getOneDaysLater(){
                    var currentDate = new Date();
                    var currentDay = currentDate.getDate();
                    currentDate.setDate( currentDay + 1 );
                    return currentDate;
                }

                //location.href = "https://login.zhubajie.com//login/dologout";
                fuName && fuName();
            });
            return win;
        }

        window["mobileVerify"] = mobileVerify;

        function changeTips(win){
            var text = $("#cert-phone").find("input[type='text']").val();
                $.ajax({
                    url:sendUrl,
                    data:"mobile="+text+"&tips=1",
                    dataType:'jsonp',
                    jsonp:"jsonpcallback",
                    success:function(json){
                        if(json.state == 1){
                            html2.find(".phone-number").html(text);
                            html2.find("input[name='vid']").val(json.vid);
                            win.resetOkButton("提交并完成绑定");
                            win.resetBody(html2);
                            html2.find(".controls").inputEmpty("请输入短信验证码");
                            function timer(target){
                                target.attr('disabled','disabled');
                                var num = 60;
                                target.html('<i></i>重发验证码(60)').removeClass("butn-green");
                                var time= setInterval(function(){
                                    if(num-- >0){
                                        target.html('<i></i>发送验证码('+num+')');
                                    } else {
                                        clearInterval(time);
                                        target.removeAttr('disabled').html('<i></i>重发验证码').addClass("butn-green");
                                    }
                                },1000);
                            }
                            html2.find("#resendcode").click(function(){
                                var resendcode = $(this);
                                if(resendcode.attr('disabled') == "disabled") return false;
                                $.ajax({
                                    url:resendUrl,
                                    data:"vid="+$("#phone-verify").find("input[name='vid']").val(),
                                    dataType:'jsonp',
                                    jsonp:"jsonpcallback",
                                    success:function(json){
                                        if(json.state == 1){
                                            timer(resendcode);
                                            ZDK.Tips(json.msg,2000,"success");
                                        }else{
                                            ZDK.Tips(json.msg,2000,"error");
                                        }
                                    }
                                });
                            });
                            timer(html2.find("#resendcode"));
                            var number = html2.find(".phone-number");
                            number.next("a").click(function(){
                                html1.find(".name,.alert").remove();
                                html1.find(".control-label").html("请重新输入手机号码");
                                win.resetBody(html1.show());
                                win.resetOkButton("下一步");
                                return false;
                            });
                        }else{
                            ZDK.Tips(json.msg,2000,"error");
                        }
                    }
                });
        }
    }

})();

/* 页面底部的浮动发布入口 */
(function(){
    function isNeedFloatEntry(){
        return $('#j-pub-entry').size();
    }
    function floatPubEntryInChannelPage(){
        $(window).scroll(function(){
            var scrollTop = parseFloat($(window).scrollTop());
            var screenHeight = parseFloat($(window).height());
            var stopTarget = $('.ui-no-appropriate');
            var $target = $('#j-pub-entry');
            if(stopTarget.length > 0) {
                var stopHeight = stopTarget.offset().top + stopTarget.outerHeight();
                var totalHeight = screenHeight + scrollTop;
                if ( scrollTop > 0 && totalHeight < stopHeight) {
                    $target.show();
                } else {
                    $target.hide();
                }
            }else{
                if( scrollTop > 10){
                    $target.show();
                }else{
                    $target.hide();
                }
            }
        });
    }

    if ( isNeedFloatEntry() ) {
        floatPubEntryInChannelPage();
    }

})();

/* 用户中心左侧的派单提示 */
(function(){
	if (!window.GetCookie) {
		window.GetCookie = ZDK.cookie.getCookie;
	}
    if( GetCookie('userid') ){
        setInviteTaskNum();
    }
    function setInviteTaskNum(){
        var inviteTaskNumObj = $('#j-invitetask-dom-tips-num');

        if ( inviteTaskNumObj.size() == 0) {
        	inviteTaskNumObj = $('<span class="menu-tips-num" id="j-invitetask-dom-tips-num"></span>')
        }
            $.getJSON( getURL() + "?jsonpcallback=?", function(json){
                if ( json.state == 1 ) {
                    renderNum( json.msg.todo );
                }
            });
        function renderNum( num ){
            num = parseInt(num);
            if (num>0) {
                inviteTaskNumObj.html( num );
                inviteTaskNumObj.show();
                inviteTaskNumObj.attr('tool-map', 'top');
                inviteTaskNumObj.attr('tool-text', '你有' + num + '个官方派单待处理');
                inviteTaskNumObj.addClass('zbj-tooltip');
                
                $('#j-my-invitetask-digit').html(num); // 服务商中心派单数字展示
                $('.ui-header-nav .ui-dropdown:contains(服务商中心)').after(
                    $('<a></a>').attr({href:'http://u.'+ZBJInfo.baseURI+"/officialtaskinvite/snatch",target:'_blank'}).append(
                    inviteTaskNumObj.clone().attr({'id':'','tool-map':'bottom','tool-cls':"snatch-topnar-tip"}).css('position','static'))
                );
            }
        }

        function getURL(){
            return 'http://u.' + ZBJInfo.baseURI + '/officialtaskinvite/count';
        }
    }

    // header中搜索框选择搜索类型
    $('#j-header-searchwrap li').click(function(){
    	var searchURI = 'http://search.' + ZBJInfo.baseURI;
        var _this = $(this);
    	var selectedType = _this.data('type'); // witkey 或者 task 或者 service
        var searchType;
        if(selectedType == 'witkey'){
            searchType = 0;
        }else if(selectedType == 'task'){
            searchType = 1;
        }else if(selectedType == 'service'){
            searchType = 2;
        }

        $('#j-header-searchlabel').text(_this.find('a').text());
        _this.hide().siblings().show();
        _this.parent().hide();
        $('#j-header-kw').focus();
        var form = $('#j-header-searchform')[0];
        if (searchType == 0) {
            form.action = searchURI+"/p/";
        } else if(searchType == 1) {
            form.action = searchURI + "/t/";
        }else if(searchType == 2){
            form.action = searchURI + "/s/";
        }
    });

    $('#j-header-searchwrap').hover(function() {
        $(this).find('.ui-dropdown-menu').show();
    },function() {
        $(this).find('.ui-dropdown-menu').hide();
    });

})();

// 模版设置
;(function(){
    $("#skinset").live('click',function(){
        $.ajax({
            url:"http://home.zhubajie.com/skin/index?jsonpcallback=?",
            type:"get",
            dataType:"json",
            success:function(json){
                if(json.state == 1){
                    var win = ZDK.module.window({
                        title:"模板设置",
                        content:json.msg,
                        cache: false,
                        width:450,
                        ok:"保存",
                        cancel:"关闭"
                    });
                    win.on("onok",function(){
                        win.Footer.find('.ui-window-ok').html("<i></i>请求中...").removeClass("small-butn-orange");
                        $.ajax({
                            url:"http://home.zhubajie.com/skin/index?jsonpcallback=?",
                            type:"post",
                            data:'type=1&showway='+ $("#ck-style .cur").attr("flag"),
                            dataType:"json",
                            success:function(json){
                                win.Footer.find('.ui-window-ok').html("<i></i>确认").addClass("small-butn-orange");
                                if(json.state == 1){
                                    ZDK.Tips("设置成功！",3000,"success");
                                    if(json.url){
                                        location.href = json.url;
                                    };
                                    win.hide();
                                }else {
                                    ZDK.Tips(json.msg,3000,"error");
                                }
                            }
                        });
                        return false;
                    });
                }
            }
        });
    });
})();

$(function(){
    var balance_holder = $("#my-balance-holder,.my-balance-holder");
    var url = "http://task." + ZBJInfo.baseURI + "/api/MyBalance";

    function loadBalance() {
        $.ajax({
            url: url,
            type: "get",
            dataType: 'jsonp',
            jsonp: 'jsonp',
            data: {pic:'1'},
            success: function(data) {
                if (data.state == 1) {
                    balance_holder.html(data.amount);
                    if (data.amount>0) {
                        $("#withdraw-link,.withdraw-link").show();
                    }
                }
            }
        });
    }
    if ( balance_holder.length > 0 ) {
    	if (location.host == 'u.'+ZBJInfo.baseURI) {
            loadBalance();
    	} else {
    		$('.item-userinfo .ui-dropdown').one('mouseover',function() {
                loadBalance();
            });
    	}
    }
});

// 请求国际部JS
(function(){
    if( document.location.href.indexOf('https://') >= 0  ){
        return;
    }
    if( document.cookie.indexOf("zbj_advert_zh") < 0 ){
        $.getScript("http://intstyle.zhubajie.com/js/int.advert.min.js?v=20131231");
    }
})();

//用户中心页面url加上时间戳防止cdn缓存
(function(){
    $('div.ui-header').delegate('div.ui-dropdown a', 'mouseover', function( evt ){
        addTimestampToUserLink( evt );
    });
    $('div.ui-header').delegate('div.lot a', 'mouseover', function( evt ){
        addTimestampToUserLink( evt );
    });
    $('#J-header-logic1').delegate('a', 'mouseover', function( evt ){
        addTimestampToUserLink( evt );
    });

    if ( checkIsUserLink( document.location.href ) ) {
        $(document.body).delegate('a', 'mouseover', function( evt ){
            addTimestampToUserLink( evt );
        })
    }

    function checkIsUserLink( href ){
        return href.indexOf('u.' + window.ZBJInfo.baseURI) > 0
    }

    function addTimestampToUserLink( evt ){
        var domObj = adjustNode( evt.target );
        var href = domObj.href;
        var timestamp = + new Date();
        if ( checkIsUserLink( href )
            && ( !/_t=\d+/.test( href ) )
            ) {

            href += ( href.indexOf('?') < 0 ? '?' : '&') + '_t=' + timestamp;
            domObj.href = href;
        }
    }

    function adjustNode( domObj ){
        while ( domObj ) {
            if ( domObj.nodeName == 'A' ) {
                return domObj;
            }
            domObj = domObj.parentNode;
        }
        return null;
    }
})();

(function(){
    var scriptLoaded = 0;

    $(document.body).delegate('#j-feedback-btn', 'mouseenter', loadFeedbackScript);

    function escapeStr( str ){
        return str.replace(/\.|\//, function( e ){
            return '\\' + e;
        });
    }

    function loadFeedbackScript(){
        if (  scriptLoaded === 1  ) {
            return;
        }
        scriptLoaded = 1;
        fetchScript();
    }

    function fetchScript(){
        $.getScript(ZBJInfo.staticURI + '/mirror/static/feedback.js', function(){
            scriptLoaded = 4;
            $(document.body).undelegate('#j-feedback-btn', 'mouseenter', loadFeedbackScript);
            $.feedback({
                proxy: 'http://slogger.zhubajie.com/html2canvasproxy.php',
                html2canvasURL: ZBJInfo.staticURI + '/mirror/static/html2canvas.js',
                feedbackBtnSelector: '#j-feedback-btn'
            });
        });
    }
})();

(function(){
    //针对静态头部进行的处理. a 或者 form 或者area带有data-process=1的节点的地址需要替换一下，
    //同时如果页面有j_current_category节点，那么在发布的url上加上-category-{categoryid}-
    if ( ZBJInfo.baseURI != 'zhubajie.com' ) {
        var tobeProcessedDoms = $('#j-zbj-header').find('a[data-process="1"], form[data-process="1"], area[data-process="1"]');
        tobeProcessedDoms.each(function( idx, item ){
            var hrefAttr = item.nodeName == 'FORM' ? 'action' : 'href';

            var href = item[hrefAttr];
            item[hrefAttr] = href.replace('zhubajie.com', ZBJInfo.baseURI);
        });
    }

    var headerPubEntey = $('#j-head-pubentry');
    var jCurrentCategory = $('#j_current_category');
    if( jCurrentCategory.size() && jCurrentCategory.size() ){
        var href = headerPubEntey.attr('href');
        headerPubEntey.attr('href', href.replace('step1', 'step1-category-' + jCurrentCategory.val()));
    }

    //如果是搜索页，还需要将搜索表单相关信息进行更正
    var labelMap = {
        'witkey': '服务商',
        'task': '需&nbsp;&nbsp;&nbsp;求',
        'service': '服&nbsp;&nbsp;&nbsp;务'
    };
    var actionMap = {
        'witkey': '/p/',
        'task': '/t/',
        'service': '/s/'
    };
    var searchType = ZBJInfo.searchType;
    if ( searchType ) {
        var formAction = $('#j-header-searchform').attr('action');
        $('#j-header-kw').val( ZBJInfo.kw );
        $('#j-header-searchlabel').html( labelMap[searchType] );
        $('#j-header-searchform').attr('action', formAction.replace('/p/', actionMap[searchType]));
        $('#j-header-searchwrap li').each(function(){
            if ($(this).data('type') == searchType){
                $(this).hide();
            } else{
                $(this).show();
            }
        });
    }

})();

//页头的mobile广告入口
(function(){

    if ( !ZDK.cookie.getCookie('tmpmobileentry') ) {
        $('#j-tmp-mobile-entry').css('visibility', 'visible');
    }
    var entryExpireDate = function(){
        var currentDate = new Date();
        var currentDay = currentDate.getDate();
        currentDate.setDate( currentDay + 360 );
        return currentDate;
    };
    $('#j-mobile-entry-close').click(function(){
        ZDK.cookie.setCookie('tmpmobileentry', 1, entryExpireDate(), '/', ZBJInfo.pageDomain);
        $('#j-tmp-mobile-entry').css('visibility', 'hidden');
    });

//    //页头的vip购买入口
//    if ( ZDK.cookie.getCookie('vipentry') ) {
//        return;
//    }
//    var topVipentry = '<div id="j-vipentry-wrap" style="background: #a71812;height: 60px;"><div class="grid" style="position: relative;"><a href="http://www.zhubajie.com/vip/index" target="_blank"><img src="http://cms.zbjimg.com/vip/vipentryindex.jpg"></a><a href="javascript:void(0)" id="j-vipentry-act" style="position: absolute;color: #fff;top: 20px;">x</a></div></div>';
//    $(topVipentry).insertAfter('div.ui-header-top');
})();

//内容发布系统
$(document).ready(function(){
    var cache = {};
    var $cmsCtn = $('.j_cms_ctn');
    function getCmsItem($item){
        var token = $item.attr('data-cmstoken');
        if(!token){
            return;
        }
        if(cache[token]){//已经在请求cms文件了，直接return
            return;
        }else{
            cache[token] = true;
            $.ajax({
                url: 'http://cms.zbjimg.com/cmsfiles/'+ token +'.js',
                jsonpCallback: '_' + token.replace(/.*\//,'') + '_js',
                dataType: 'jsonp',
                success: function(res){
                    var content = res.content.split('##split##'); //缓存
                    $cmsCtn.filter('[data-cmstoken="'+ token +'"]').each(function(i, o){
                        var $this = $(this);
                        var index = $this.attr('data-cmsindex') || 0;
                        $this.html(content[index] || '');
                    });
                }
            });
        }
    }
    $cmsCtn.each(function(i, o){
        var $item = $(o);
        getCmsItem($item);
    });
});
