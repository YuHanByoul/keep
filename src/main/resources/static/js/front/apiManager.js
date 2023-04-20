var apiManager = {
    init: function() {
        this.registEvent();    
    }
    
    , registEvent: function() {
        // TODO
    }
    
    // 실시간 대기정보
    , getCtprvnRltmMesureDnsty: function(sidoName, tabIdx, grid) {
        $.ajax({
            url: "/front/api/getCtprvnRltmMesureDnsty.do"
            , type: "GET"
            , data: {sidoName: sidoName} 
            , cache: false
            , success: function(result) {
                if(result.response && result.response.header.resultCode == "00") {
                    if(main.envfldCd != "104101") return false;
                    main.setWidgetStatus("success", result.response.body.items[0].dataTime + "기준");
                    apiManager.setCtprvnRltmMesureDnsty(result.response.body, tabIdx, grid);
                } else {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setCtprvnRltmMesureDnsty: function(data, tabIdx, grid) {
        for(var i = 0; i < data.totalCount; i++) {
            var info = data.items[i];
            var sidoName = info.sidoName;
            var stationName = info.stationName;
            var displayInfo = this.getRltmMesureDnstyInfo(tabIdx, info);
            
            $(grid).append(
                '<tr><td>' + sidoName + '</td><td>' + stationName + '</td><td><span class="fc-level' + displayInfo.grade + '">' + displayInfo.value + '</span></td></tr>'
            );
        }
    }
    
    // 대기 노출 정보
    , getRltmMesureDnstyInfo: function(idx, data) {
        var info = {
          value: "-"
          , grade: "-"  
        };
        switch(idx) {
            case 0:
                info.grade = data.khaiGrade;
                info.value = this.getGrade(data.khaiGrade) + "(" + data.khaiValue + ")"; 
            break;
            
            case 1:
                info.grade = data.pm25Grade;
                info.value = data.pm25Value + "㎍/㎥(1시간),<br/>" + data.pm25Value24 + "㎍/㎥(24시간)";
            break;
            
            case 2:
                info.grade = data.pm10Grade;
                info.value = data.pm10Value + "㎍/㎥(1시간),<br/>" + data.pm10Value24 + "㎍/㎥(24시간)";
            break;
            
            case 3:
                info.grade = data.o3Grade; 
                info.value = data.o3Value;
            break;
            
            case 4:
                info.grade = data.no2Grade;
                info.value = data.no2Value;
            break;
            
            case 5:
                info.grade = data.coGrade;
                info.value = data.coValue;
            break;
            
            case 6:
                info.grade = data.so2Grade;
                info.value = data.so2Value;
            break;
            
            default:
        }
        
        return info;
    }
    
    // 대기 상태
    , getGrade: function(grade) {
        return grade == 1 ? "좋음" : grade == 2 ? "보통" : grade == 3 ? "나쁨" : grade == 4 ? "매우나쁨" : "-";
    }
    
    // 오늘/내일/모레 예보
    , getMinuDustFrcstDspth: function(informCode, dateIdx, grid) {
        $.ajax({
            url: "/front/api/getMinuDustFrcstDspth.do"
            , type: "GET"
            , data: {searchDate: $.datepicker.formatDate("yy-mm-dd", new Date()), informCode: informCode} 
            , cache: false
            , success: function(result) {
                if(main.envfldCd != "104102") return false;
                if(result.response && result.response.header.resultCode == "00") {
                    apiManager.setMinuDustFrcstDspth(result.response.body, dateIdx, grid);
                } else {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setMinuDustFrcstDspth: function(data, dateIdx, grid) {
        var now = new Date();
        var today = $.datepicker.formatDate("yy-mm-dd", now);
        var tomorrow = $.datepicker.formatDate("yy-mm-dd", new Date(now.setDate(now.getDate() + 1)));
        var afterTomorrow = $.datepicker.formatDate("yy-mm-dd", new Date(now.setDate(now.getDate() + 1)));
        var todayInfoList = "예보정보가 없습니다.", tomorrowInfoList = "예보정보가 없습니다.", afterTomorrowInfoList = "예보정보가 없습니다.";
        var dateTime;
        for(var i = 0, len = data.totalCount; i < len; i++) {
            var info = data.items[i];
            if(info.informData == today && todayInfoList == "예보정보가 없습니다.") {
                dateTime = info.dataTime;
                todayInfoList = info.informGrade.split(",");
            } else if(info.informData == tomorrow && tomorrowInfoList == "예보정보가 없습니다.") {
                dateTime = info.dataTime;
                tomorrowInfoList = info.informGrade.split(",");
            } else if(info.informData == afterTomorrow && afterTomorrowInfoList == "예보정보가 없습니다.") {
                dateTime = info.dataTime;
                afterTomorrowInfoList = info.informGrade.split(",");
            }
        }
        if((dateIdx == 0 && todayInfoList == "예보정보가 없습니다.") || (dateIdx == 1 && tomorrowInfoList == "예보정보가 없습니다.") || 
          (dateIdx == 2 && afterTomorrowInfoList == "예보정보가 없습니다.")) {
            main.setWidgetStatus("error", null, "예보정보가 없습니다.");
        } else {
            var data, area, grade, className;
            var list = dateIdx == 0 ? todayInfoList : dateIdx == 1 ? tomorrowInfoList : afterTomorrowInfoList;
            for(var i = 0, len = list.length; i < len; i++) {
                data = list[i];
                area = $.trim(data.split(":")[0]);
                grade = $.trim(data.split(":")[1]);
                className = grade == "좋음" ? "fc-level1" : grade == "보통" ? "fc-level2" : grade == "나쁨" ? "fc-level3" : "fc-level4";
                $(grid).append(
                    '<tr><td>' + area + '</td>' + '<td><span class="' + className + '"><i></i>' + grade + '</span></td></tr>' 
                ); 
            }
            main.setWidgetStatus("success", dateTime);
        }
    }
    
    // 우리동네 대기정보
    , getMsrstnAcctoRltmMesureDnsty: function(stationName, grid) {
        $.ajax({
            url: "/front/api/getMsrstnAcctoRltmMesureDnsty.do"
            , type: "GET"
            , data: {stationName: stationName} 
            , cache: false
            , success: function(result) {
                if(result.response && result.response.header.resultCode == "00") {
                    if(main.envfldCd != "104103") return false;
                    if(result.response.body.items.length == 0) {
                        main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                    } else {
                        main.setWidgetStatus("success", result.response.body.items[0].dataTime + "기준");
                        apiManager.setMsrstnAcctoRltmMesureDnsty(result.response.body, grid);
                    }
                } else {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setMsrstnAcctoRltmMesureDnsty: function(data, grid) {
        var info = data.items[0];
        $(grid).append(
            '<tr><td class="al pt5 pb5">초미세먼지(PM-2.5)</td><td class="al pt5 pb5"><span class="fc-level' + info.pm25Grade + '"><i></i>' + this.getGrade(info.pm25Grade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.pm25Value + '㎍/㎥(1h), ' + info.pm25Value24 + '㎍/㎥(24h)</td></tr>' 
        );
        $(grid).append(
            '<tr><td class="al pt5 pb5">미세먼지(PM-10)</td><td class="al pt5 pb5"><span class="fc-level' + info.pm10Grade + '"><i></i>' + this.getGrade(info.pm25Grade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.pm10Value + '㎍/㎥(1h), ' + info.pm10Value24 + '㎍/㎥(24h)</td></tr>' 
        );
        $(grid).append(
            '<tr><td class="al pt5 pb5">오존(O3)</td><td class="al pt5 pb5"><span class="fc-level' + info.o3Grade + '"><i></i>' + this.getGrade(info.o3Grade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.o3Value + 'ppm</td></tr>' 
        );
        $(grid).append(
            '<tr><td class="al pt5 pb5">이산화질소(NO2)</td><td class="al pt5 pb5"><span class="fc-level' + info.no2Grade + '"><i></i>' + this.getGrade(info.no2Grade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.no2Value + 'ppm</td></tr>' 
        );
        $(grid).append(
            '<tr><td class="al pt5 pb5">일산화탄소(CO)</td><td class="al pt5 pb5"><span class="fc-level' + info.coGrade + '"><i></i>' + this.getGrade(info.coGrade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.coValue + 'ppm</td></tr>' 
        );
        $(grid).append(
            '<tr><td class="al pt5 pb5">아황산가스(SO2)</td><td class="al pt5 pb5"><span class="fc-level' + info.so2Grade + '"><i></i>' + this.getGrade(info.so2Grade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.so2Value + 'ppm</td></tr>' 
        );
    }
    
    , onPostPopup: function() {
        new daum.Postcode({
            oncomplete: function(data) {
                apiManager.getTMStdrCrdnt(data.bname).then(function(response) {
                    if(main.envfldCd != "104103") return false;
                    if(response.totalCount == 0) {
                        main.setWidgetStatus("error", null, "검색한 주소와 가까운 측정소가 없습니다.<br/>주소를 다시 검색해 주세요.");
                    } else {
                        var info, item;
                        for(var i = 0; i < response.totalCount; i++) {
                            item = response.items[i];
                            if(item.sggName == data.sigungu) {
                                info = item;
                                break;
                            }
                        }
                        apiManager.getNearbyMsrstnList(data, info);                        
                    }
                }).catch(function(error) {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                });
            }
        }).open();
    }
    
    , getTMStdrCrdnt: function(umdName) {
        return new Promise(function(resolve, reject) {
            $.ajax({
                url: "/front/api/getTMStdrCrdnt.do"
                , type: "GET"
                , data: {umdName: umdName} 
                , cache: false
                , success: function(result) {
                    if(main.envfldCd != "104103") return false;
                    if(result.response && result.response.header.resultCode == "00") {
                        resolve(result.response.body);
                    } else {
                        reject(new Error("서비스 제공상태가 원할하지 않습니다."));
                    }
                }
            });
        });
    }
    
    , getNearbyMsrstnList: function(postData, tmData) {
        $.ajax({
            url: "/front/api/getNearbyMsrstnList.do"
            , type: "GET"
            , data: {tmX: tmData.tmX, tmY: tmData.tmY} 
            , cache: false
            , success: function(result) {
                if(main.envfldCd != "104103") return false;
                if(result.response && result.response.header.resultCode == "00") {
                    var stationInfo = result.response.body.items[0];
                    var confirmMsg = postData.address + "(" + postData.bname + ")" + "과(와)\n가장 가까운 측정소는 『" + stationInfo.stationName + " 측정소』입니다.\n해당 측정소의 대기정보를 조회 하시겠습니까?";
                    if(confirm(confirmMsg)) {
                        localStorage.setItem("nearMsrstn", stationInfo.stationName);
                        main.onSearch();    
                    }
                } else {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    // 수위정보
    , getWaterlevelList: function(sidoName, grid) {
        $.ajax({
            url: "/front/api/getWlobsList.do"
            , type: "GET"
            , data: {hydroType: "waterlevel"} 
            , cache: false
            , success: function(result) {
                try {
                    if(main.envfldCd != "104104") return false;
                    apiManager.getWlobsList(sidoName, result.wlobsList.content, result.obsrvnList.content, "water", grid);
                } catch {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setWaterlevelList: function(dataList, grid, sidoName) {
        var data, obsnm, attwl, wrnwl, almwl, srswl, wl;
        for(var i = 0, len = dataList.length; i < len; i++) {
            data = dataList[i];
            obsnm = data.obsnm; 
            attwl = data.attwl == " " ? "-" : data.attwl; 
            wrnwl = data.wrnwl == " " ? "-" : data.wrnwl; 
            almwl = data.almwl == " " ? "-" : data.almwl; 
            srswl = data.srswl == " " ? "-" : data.srswl; 
            wl = data.wl == " " ? "-" : data.wl; 
            $(grid).append(
                '<tr><td>' + sidoName + '</td><td>' + obsnm + '</td>' + '<td>' + attwl + '</td>' + 
                '<td>' + wrnwl + '</td>' + '<td>' + almwl + '</td>' + 
                '<td>' + srswl + '</td>' + '<td>' + wl + '</td></tr>'
            );            
        }
    }
    
    // 댐정보
    , getDamList: function(grid) {
        $.ajax({
            url: "/front/api/getWlobsList.do"
            , type: "GET"
            , data: {hydroType: "dam"} 
            , cache: false
            , success: function(result) {
                try {
                    if(main.envfldCd != "104104") return false;
                    apiManager.getWlobsList("", result.wlobsList.content, result.obsrvnList.content, "dam", grid);
                } catch {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setDamList: function(dataList, grid, sidoName) {
        var data, sidoName, obsnm, pfh, swl, inf, tototf;
        for(var i = 0, len = dataList.length; i < len; i++) {
            data = dataList[i];
            sidoName = data.sidoName;
            obsnm = data.obsnm; 
            pfh = data.pfh == " " ? "-" : data.pfh; 
            swl = data.swl == " " ? "-" : data.swl; 
            inf = data.inf == " " ? "-" : data.inf; 
            tototf = data.tototf == " " ? "-" : data.tototf; 
            $(grid).append(
                '<tr><td>' + obsnm + '</td>' + '<td>' + pfh + '</td>' + 
                '<td>' + swl + '</td>' + '<td>' + inf + '</td>' + '<td>' + tototf + '</td></tr>'
            );            
        }
    }
    
    // 보정보
    , getBoList: function(grid) {
        $.ajax({
            url: "/front/api/getWlobsList.do"
            , type: "GET"
            , data: {hydroType: "bo"} 
            , cache: false
            , success: function(result) {
                try {
                    if(main.envfldCd != "104104") return false;
                    apiManager.getWlobsList("", result.wlobsList.content, result.obsrvnList.content, "bo", grid);
                } catch {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setBoList: function(dataList, grid) {
        var data, sidoName, obsnm, pfh, swl, owl, inf, tototf;
        for(var i = 0, len = dataList.length; i < len; i++) {
            data = dataList[i];
            sidoName = data.sidoName;
            obsnm = data.obsnm; 
            pfh = data.pfh == " " ? "-" : data.pfh; 
            swl = data.swl == " " ? "-" : data.swl; 
            owl = data.owl == " " ? "-" : data.owl; 
            inf = data.inf == " " ? "-" : data.inf; 
            tototf = data.tototf == " " ? "-" : data.tototf; 
            $(grid).append(
                '<tr><td style="word-break: keep-all;">' + obsnm + '</td>' + '<td>' + pfh + '</td>' + '<td>' + swl + '</td>' + 
                '<td>' + owl + '</td>' + '<td>' + inf + '</td>' + '<td>' + tototf + '</td></tr>'
            );            
        }
    }
    
    , getWlobsList: function(sidoName, wlobsList, obsrvnList, type, grid) {
        var list = [];
        var addr;
        for(var i = 0, len = wlobsList.length; i < len; i++) {
            var data = wlobsList[i];
            if(data) {
                addr = data.addr;
                if(addr && addr != " ") {
                    addr = addr.split(" ")[0];
                    if(type == "water") {
                        if(sidoName == "충북" && addr.indexOf("충청북도") > -1) {
                            list.push(data);
                        } else if(sidoName == "충남" && addr.indexOf("충청남도") > -1) {
                            list.push(data);
                        } else if(sidoName == "경북" && (addr.indexOf("경상북도") > -1 || addr.indexOf("경북") > -1 || addr.indexOf("겅상북도") > -1)) {
                            list.push(data);
                        } else if(sidoName == "경남" && addr.indexOf("경상남도") > -1) {
                            list.push(data);
                        } else if(sidoName == "전북" && (addr.indexOf("전라북도") > -1 || addr.indexOf("전북") > -1)) {
                            list.push(data);
                        } else if(sidoName == "전남" && addr.indexOf("전라남도") > -1) {
                            list.push(data);
                        } else if(addr.indexOf(sidoName) > -1) {
                            list.push(data);
                        }    
                    } else {
                        if(addr.indexOf("충청북도") > -1) {
                            data.sidoName = "충북";
                        } else if(addr.indexOf("충청남도") > -1) {
                            data.sidoName = "충남";
                        } else if((addr.indexOf("경상북도") > -1 || addr.indexOf("경북") > -1 || addr.indexOf("겅상북도") > -1)) {
                            data.sidoName = "경북";
                        } else if(addr.indexOf("경상남도") > -1) {
                            data.sidoName = "경남";
                        } else if((addr.indexOf("전라북도") > -1 || addr.indexOf("전북") > -1)) {
                            data.sidoName = "전북";
                        } else if(addr.indexOf("전라남도") > -1) {
                            data.sidoName = "전남";
                        } else {
                            data.sidoName = addr.split(" ")[0];                       
                        }
                        list.push(data);                        
                    }
                }
            }
        }
        if(type == "water") {
            for(i = 0, len = list.length; i < len; i++) {
                var wlobsInfo = list[i];
                var obsrvnInfo = obsrvnList.find(function(item) {
                    return item.wlobscd == wlobsInfo.wlobscd;
                });
                wlobsInfo["wl"] = obsrvnInfo ? obsrvnInfo.wl : "-";
            }
            this.setWaterlevelList(list, grid, sidoName);
        } else if(type == "dam") {
            for(i = 0, len = list.length; i < len; i++) {
                var wlobsInfo = list[i];
                var obsrvnInfo = obsrvnList.find(function(item) {
                    return item.dmobscd == wlobsInfo.dmobscd;
                });
                wlobsInfo["swl"] = obsrvnInfo ? obsrvnInfo.swl : "-";
                wlobsInfo["inf"] = obsrvnInfo ? obsrvnInfo.inf : "-";
                wlobsInfo["tototf"] = obsrvnInfo ? obsrvnInfo.tototf : "-";
            }
            this.setDamList(list, grid);
        } else if(type == "bo") {
            for(i = 0, len = list.length; i < len; i++) {
                var wlobsInfo = list[i];
                var obsrvnInfo = obsrvnList.find(function(item) {
                    return item.boobscd == wlobsInfo.boobscd;
                });
                wlobsInfo["swl"] = obsrvnInfo ? obsrvnInfo.swl : "-";
                wlobsInfo["owl"] = obsrvnInfo ? obsrvnInfo.owl : "-";
                wlobsInfo["inf"] = obsrvnInfo ? obsrvnInfo.inf : "-";
                wlobsInfo["tototf"] = obsrvnInfo ? obsrvnInfo.tototf : "-";
            }
            this.setBoList(list, grid);
        }
        main.setWidgetStatus("success", null);
    }
    
    // 농업기상 관측지점 정보
    , getObsrSpotList: function(doSeCode) {
        $.ajax({
            url: "/front/api/getObsrSpotList.do"
            , type: "GET"
            , data: {doSeCode: doSeCode} 
            , cache: false
            , success: function(result) {
                try {
                    if(main.envfldCd != "104105") return false;
                } catch {
                    //console.log("서비스 제공상태가 원할하지 않습니다.");
                }
            }
        });
    }
    
    // 농업기상 관측 정보 조회(시간)
    , getWeatherTimeList: function(obsrSpotCode, grid) {
        $.ajax({
            url: "/front/api/getWeatherTimeList.do"
            , type: "GET"
            , data: {dateTime: $.datepicker.formatDate("yy-mm-dd", new Date()), obsrSpotCode: obsrSpotCode} 
            , cache: false
            , success: function(result) {
                try {
                    if(result.response) {
                        if(main.envfldCd != "104105") return false;
                        main.setWidgetStatus("success", null);
                        apiManager.setWeatherTimeList(result.response.body, grid);
                    } else {
                        main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                    }
                } catch {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setWeatherTimeList: function(data, grid) {
        var dataList = data.items.item;
        var len = data.total_Count;
        var info, time;
        for(var i = data.total_Count - 3; i < len; i++) {
            info = dataList[i];
            time = info.date.split(" ")[1].split(":")[0] + "시";
            $("#time" + (3 - (len - i))).text(time);
        }
        $(grid).append('<tr><td>기온</td><td>' + dataList[0].temp + '℃</td><td>' + dataList[1].temp + '℃</td><td>' + dataList[2].temp + '℃</td><tr>');
        $(grid).append('<tr><td>습도</td><td>' + dataList[0].hum + '%</td><td>' + dataList[1].hum + '%</td><td>' + dataList[2].hum + '%</td><tr>');
        $(grid).append('<tr><td>강수량</td><td>' + dataList[0].rain + '%</td><td>' + dataList[1].rain + '%</td><td>' + dataList[2].rain + '%</td><tr>');
        var sunTime0 = dataList[0].sun_Time ? dataList[0].sun_Time : "-";
        var sunTime1 = dataList[1].sun_Time ? dataList[1].sun_Time : "-";
        var sunTime2 = dataList[2].sun_Time ? dataList[2].sun_Time : "-";
        $(grid).append('<tr><td>일조시간</td><td>' + sunTime0 + '</td><td>' + sunTime1 + '</td><td>' + sunTime2 + '</td><tr>');
    }
    
    // 농업기상 관측 정보 조회(일)
    , getWeatherMonDayList: function(obsrSpotCode, grid) {
        $.ajax({
            url: "/front/api/getWeatherMonDayList.do"
            , type: "GET"
            , data: {searchYear: $.datepicker.formatDate("yy", new Date()), searchMonth: $.datepicker.formatDate("mm", new Date()), obsrSpotCode: obsrSpotCode} 
            , cache: false
            , success: function(result) {
                try {
                    if(result.response) {
                        if(main.envfldCd != "104105") return false;
                        main.setWidgetStatus("success", null);
                        apiManager.setWeatherMonDayList(result.response.body, grid);
                    } else {
                        main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                    }
                } catch {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setWeatherMonDayList: function(data, grid) {
        var dataList = data.items.item;
        var len = data.total_Count - 5;
        if(len < 5) {
            len = data.total_Count;
        }
        var info, date, temp, maxTemp, minTemp, sunTime, rain;
        for(var i = len - 1; i >= 0; i--) {
            info = dataList[i];
            date = info.date.split("-")[1] + "월" + info.date.split("-")[2] + "일";
            temp = info.temp;
            maxTemp = info.max_Temp;
            minTemp = info.min_Temp;
            sunTime = info.sun_Time ? info.sun_Time : "-";
            rain = info.rain;
            $(grid).append(
                '<tr><td>' + date + '</td>' + '<td>' + temp + '℃</td>' + '<td>' + maxTemp + '℃</td>' + 
                '<td>' + minTemp + '℃</td>' + '<td>' + sunTime + '</td>' + '<td>' + rain + 'mm</td></tr>'
            );
        }
    }
    
    // 전기자동차 충전소 정보
    , getChargerInfo: function(zcode, zscode, grid) {
        $.ajax({
            url: "/front/api/getChargerInfo.do"
            , type: "GET"
            , data: {zcode: zcode, zscode: zscode} 
            , cache: false
            , success: function(result) {
                try {
                    if(main.envfldCd != "104106") return false;
                    if(result.resultCode && result.resultCode == "00") {
                        main.setWidgetStatus("success", null);
                        apiManager.setChargerInfo(result, grid);
                    } else {
                        main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");                                                                                
                    }
                } catch {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setChargerInfo: function(data, grid) {
        var dataList = data.items.item;
        var len = data.totalCount;
        var info, statNm, type, stat, addr;
        for(var i = 0; i < len; i++) {
            info = dataList[i];
            statNm = info.statNm;
            type = info.chgerType == "01" ? "DC차데모" : info.chgerType == "02" ? "AC완속" : info.chgerType == "03" ? "DC차데모 + AC3상" :
                     info.chgerType == "04" ? "DC콤보" : info.chgerType == "05" ? "DC차데모 + DC콤보" : info.chgerType == "06" ? "DC차데모 + AC3상 + DC콤보" : 
                     info.chgerType == "07" ? "AC3상" : "-";
            stat = info.stat == "1" ? "통신이상" : info.stat == "2" ? "대기" : info.stat == "3" ? "충전중" : info.stat == "4" ? "운영중지" :
                    info.stat == "5" ? "점검중" : info.stat == "9" ? "미확인" : "-";
            addr = info.addr;
            $(grid).append(
                '<tr><td>' + statNm + '</td>' + '<td>' + type + '</td>' + '<td>' + stat + '</td>' + '<td>' + addr + '</td></tr>'
            );
        }
    }
};