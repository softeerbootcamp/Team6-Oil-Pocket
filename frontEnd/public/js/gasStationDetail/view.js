import { navBarView } from "../navbar/view";
import { gasStationDetailTemplate } from "./template";

const gasSTDView = () => {
    const $gasSTDViewViewContainer = document.createElement("section");
    const $gasSTDViewViewContent = document.createElement("section");
    $gasSTDViewViewContent.classList.add("main");

    $gasSTDViewViewContent.innerHTML = gasStationDetailTemplate();

    $gasSTDViewViewContainer.appendChild(navBarView());
    $gasSTDViewViewContainer.appendChild($gasSTDViewViewContent);

    return $gasSTDViewViewContainer;
}

export { gasSTDView }