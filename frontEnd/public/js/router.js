import { mainView } from "./main/view.js";
import { loginView } from "./login/view.js";
import { registerView } from "./register/view.js";
import { userDetailView } from "./myPage/userDetail/view.js";
import { chartView } from "./myPage/chart/view.js";
import { comparisonView } from "./myPage/comparison/view.js";
import { inputOilInfoView } from "./myPage/inputOilInfo/view.js";
import { historyView } from "./myPage/history/view.js";
import { notFoundView } from "./notFound/view.js";
import { _$ } from "./common/utils.js";
import { fecthCheckLogin, routerCheckLogin } from "./login/fetch.js";
import { mapView } from "./mapView/view.js";
import { initTmap } from "./mapView/helperFunction.js";

const $body = _$("body");

const router = async () => {
    if(location.pathname === "/login" || location.pathname === "/register") {
        await routerCheckLogin();
    }
    else {
        await fecthCheckLogin();
    }

    const routes = [
        { path: "/", view: mainView },
        { path: "/register", view: registerView },
        { path: "/login", view: loginView },
        { path: "/userDetail", view: userDetailView },
        { path: "/inputOilInfo", view: inputOilInfoView },
        { path: "/chart", view: chartView },
        { path: "/comparison", view: comparisonView },
        { path: "/history", view: historyView },
        { path: "/mapView", view: mapView },
        { path: "/404", view: notFoundView }
    ];
    
    let match = routes.map(route => {
        return {
            route,
            isMatch: location.pathname === route.path
        };
    }).find((routeObj) => routeObj.isMatch)
    
    if(!match) {
        match = {
            route: routes[routes.length - 1],
            isMatch: true
        }
    }
    const getNode = match.route.view;
    const $container = await getNode();

    $body.replaceWith($container);

    if(location.pathname === "/mapView") {
        initTmap();
    }
}
// 페이지 전환 함수
const navigateTo = url => {
    history.pushState(null, null, url);
    router();
}
window.addEventListener("popstate", () => router());
// window.addEventListener("DOMContentLoaded", () => router());
// DOM이 렌더링 되면 router 함수 실행
document.addEventListener("DOMContentLoaded", () => {
    document.body.addEventListener("click", (event) => {
        if(event.target.matches("[data-link]")) {
            event.preventDefault();
            navigateTo((event.target.href));
        }
    })
    router();
});

navigator.geolocation.getCurrentPosition((position) =>{
    localStorage.setItem("longitude", position.coords.longitude);
    localStorage.setItem("latitude", position.coords.latitude);
})
