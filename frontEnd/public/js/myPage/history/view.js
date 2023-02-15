import { navBarView } from "../../navbar/view.js";
import { getHistoryTemplate } from "./template.js";

const historyView = async () => {
    const $historyContainer = document.createElement("section");
    const $historyContent = document.createElement("section");
    $historyContent.classList.add("main");

    $historyContent.innerHTML = getHistoryTemplate();

    $historyContainer.appendChild(navBarView());
    $historyContainer.appendChild($historyContent);

    return $historyContainer;
}

export { historyView }