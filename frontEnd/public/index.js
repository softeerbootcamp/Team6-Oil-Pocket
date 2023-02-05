import { mainView } from "./main/view.js";
import { loginView } from "./login/view.js";
import { registerView } from "./register/view.js";

import { showNotFoundView } from "./views/notFoundView.js";
import { userDetailView } from "./views/mypage/userDetailView.js";
import { inputOilInfoView } from "./views/mypage/inputOilInfo.js";
import { chartView } from "./views/mypage/chart.js";
import { comparisonView } from "./views/mypage/comparison.js";
import { historyView } from "./views/mypage/history.js";


const $body = document.querySelector("body");

const router = async () => {
    const routes = [
        { path: "/", view: mainView }, 
        { path: "/register", view: registerView },
        { path: "/login", view: loginView },
        { path: "/userDetail", view: userDetailView },
        { path: "/inputOilInfo", view: inputOilInfoView },
        { path: "/chart", view: chartView },
        { path: "/comparison", view: comparisonView },
        { path: "/history", view: historyView },
        { path: "/404", view: showNotFoundView }
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
    $body.replaceWith(getNode());
}

// 페이지 전환 함수
const navigateTo = url => {
    history.pushState(null, null, url);
    router();
}

window.addEventListener("popstate", () => router());
window.addEventListener("DOMContentLoaded", () => router());

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