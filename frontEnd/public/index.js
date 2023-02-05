import { loginView } from "./views/loginView.js";
import { mainView } from "./views/mainView.js";
import { showNotFoundView } from "./views/notFoundView.js";
import { reigsterView } from "./views/registerView.js";

const $body = document.querySelector("body");
const basePath = "/frontEnd";

const router = async () => {

    const routes = [
        { path: "/", view: mainView }, 
        { path: "/index.html", view: mainView },
        { path: "/register", view: reigsterView },
        { path: "/login", view: loginView },
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
        if(event.target.matches("[data-link]")) {
            event.preventDefault();
            navigateTo((event.target.href));
        }
    })

    router();
});