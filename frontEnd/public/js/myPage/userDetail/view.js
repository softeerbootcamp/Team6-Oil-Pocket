import { navBarView } from "../../navbar/view.js";
import { eventToUserDetailModifyBtn } from "./event.js";
import { getUserDetailTemplate } from "./template.js";

const userDetailView = async () => {
    const $userDetailContainer = document.createElement("section");
    const $userDetailContent = document.createElement("section");
    $userDetailContent.classList.add("main");
    $userDetailContent.innerHTML = getUserDetailTemplate();

    $userDetailContainer.appendChild(navBarView());
    $userDetailContainer.appendChild($userDetailContent);

    eventToUserDetailModifyBtn($userDetailContainer)

    return $userDetailContainer;
}

export { userDetailView }