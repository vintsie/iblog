/**
 * Created by Vin on 14-5-22.
 */
function check_os() {
    var windows = (navigator.userAgent.indexOf("Windows",0) != -1)?1:0;
    var mac = (navigator.userAgent.indexOf("mac",0) != -1)?1:0;
    var linux = (navigator.userAgent.indexOf("Linux",0) != -1)?1:0;
    var unix = (navigator.userAgent.indexOf("X11",0) != -1)?1:0;
    var os_type;
    if (windows) os_type = "MS Windows";
    else if (mac) os_type = "Apple mac";
    else if (linux) os_type = "Linux";
    else if (unix) os_type = "Unix";
    //alert(os_type);
    return os_type;
}

function optimize_style(){
    if("Linux" == check_os()){
        // linux的Footer调整最小高度
        $("section.text-footer").css("min-height", "50px");
    }
}