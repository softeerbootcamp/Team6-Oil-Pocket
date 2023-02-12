import { navBarView } from "../../navbar/view.js";
import { eventToOilPriceInput, eventToOilSelectArea, eventToOilSearchInput } from "./event.js";
import { getInputOilInfoTemplate } from "./template.js";

const inputOilInfoView = () => {
    const $inputOilInputContainer = document.createElement("section");
    const $inputOilInputContent = document.createElement("section");
    $inputOilInputContent.classList.add("main");

    $inputOilInputContent.innerHTML = getInputOilInfoTemplate();

    $inputOilInputContainer.appendChild(navBarView());
    $inputOilInputContainer.appendChild($inputOilInputContent);

    eventToOilSelectArea($inputOilInputContent);
    eventToOilPriceInput($inputOilInputContent);
    eventToOilSearchInput($inputOilInputContent);

    return $inputOilInputContainer;
}

export { inputOilInfoView }