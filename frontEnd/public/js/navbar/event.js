import { addEvent, _$ } from "../common/function"
import { fetchLogoutID } from "../login/fetch";

const eventToLogoutBtn = ($container) => {
    const $logoutBtn = _$(".navbar__myPageBtn", $container);
    addEvent($logoutBtn, [fetchLogoutID]);
}

export { eventToLogoutBtn }