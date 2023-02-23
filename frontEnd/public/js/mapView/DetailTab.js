let DetailTabDisplay = false;
let SideBarDisplay = true;
function ShowDetailTab(){
    const GSTDetailTab = document.getElementsByClassName("main__GSTDetailTab");
    GSTDetailTab[0].style.marginLeft = "0vw";
    const SideSearchBar = document.getElementsByClassName("main__SideSearchBar");
    SideSearchBar[0].style.borderRadius = "0";
    DetailTabDisplay = true;
}

function appendNewChart(){
    document.getElementById('myChart').remove();
    const newChart = document.createElement('canvas');
    newChart.id= 'myChart'; 

    document.getElementsByClassName('main__GSTdetail__Contents__Chart')[0].append(newChart);
}

function closeDetailTab() {
    const GSTDetailTab = document.getElementsByClassName("main__GSTDetailTab");
    GSTDetailTab[0].style.marginLeft = "-22vw";
    const SideSearchBar = document.getElementsByClassName("main__SideSearchBar");
    SideSearchBar[0].style.borderRadius = "0 5px 5px 0";
    DetailTabDisplay = false;
}

function MoveSideBar(e){
    if(SideBarDisplay){
        HideSideBar();
    }
    else {
        ShowSideBar();
    }
}

function ShowSideBar(){
    const SideBar = document.getElementsByClassName("main__SearchBarnDetailTab");
    const HideSideBarButton = document.getElementsByClassName("main__SearchBarHideButton");

    SideBar[0].style.marginLeft = "0vw";
    SideBarDisplay = true;
    HideSideBarButton.innerHTML = "<img src='img/🦆 icon _chevron left_.svg'>";
}

function HideSideBar(){
    const SideBar = document.getElementsByClassName("main__SearchBarnDetailTab");
    const HideSideBarButton = document.getElementsByClassName("main__SearchBarHideButton");
    if(DetailTabDisplay){
        SideBar[0].style.marginLeft = "-44vw";
    }
    else {
        SideBar[0].style.marginLeft = "-22vw";
    }
    SideBarDisplay = false;
    HideSideBarButton.innerHTML = "<img src='img/🦆 icon _chevron right_.svg'>";

}

export {ShowDetailTab, appendNewChart, closeDetailTab, DetailTabDisplay, MoveSideBar, ShowSideBar, HideSideBar, SideBarDisplay}