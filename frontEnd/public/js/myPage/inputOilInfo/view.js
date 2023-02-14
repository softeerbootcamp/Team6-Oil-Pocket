import { navBarView } from "../../navbar/view.js";
import { eventToOilPriceInput, eventToOilSelectArea, eventToOilSearchInput, eventToRegisterBtn, eventToSearchValue } from "./event.js";
import { getInputOilInfoTemplate, getSearchTemplate } from "./template.js";

const inputOilInfoView = async () => {
    const $inputOilInputContainer = document.createElement("section");
    const $inputOilInputContent = document.createElement("section");
    $inputOilInputContent.classList.add("main");

    $inputOilInputContent.innerHTML = getInputOilInfoTemplate();

    $inputOilInputContainer.appendChild(navBarView());
    $inputOilInputContainer.appendChild($inputOilInputContent);

    eventToOilSelectArea($inputOilInputContent);
    eventToOilPriceInput($inputOilInputContent);
    eventToOilSearchInput($inputOilInputContent);
    eventToRegisterBtn($inputOilInputContent);

    return $inputOilInputContainer;
}

const gasStationSearchView = (stationName, address, stationNo) => {
    const $gasSearchSection = document.createElement("section");
    $gasSearchSection.classList.add("oilInfoArea__oilSearchValue");
    $gasSearchSection.dataset.stationNo = stationNo;
    $gasSearchSection.innerHTML = getSearchTemplate(stationName, address);

    eventToSearchValue($gasSearchSection);

    return $gasSearchSection;
}

export { inputOilInfoView, gasStationSearchView }