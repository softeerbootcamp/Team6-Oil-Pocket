import { loginView } from "./views/loginView.js";
import { mainView } from "./views/mainView.js";
import { showNotFoundView } from "./views/notFoundView.js";

const $body = document.querySelector("body");
const basePath = "/frontEnd";

const router = async () => {

    const routes = [
        { path: basePath + "/", view: mainView }, 
        { path: basePath + "/index.html", view: mainView },
        { path: basePath + "/login", view: loginView },
        { path: basePath + "/404", view: showNotFoundView }
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

    const view = match.route.view;
    $body.innerHTML = view;
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
        event.preventDefault();
        navigateTo((event.target.href));
    })

    router();
});