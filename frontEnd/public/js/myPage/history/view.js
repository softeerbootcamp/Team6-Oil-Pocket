import { navBarView } from "../../navbar/view.js";
import { fetchOilHistory } from "./fetch.js";
import { getHistoryRowTemplate, getHistoryTemplate } from "./template.js";

const historyView = async () => {
    const $historyContainer = document.createElement("section");
    const $historyContent = document.createElement("section");
    $historyContent.classList.add("main");

    $historyContent.innerHTML = getHistoryTemplate();

    $historyContainer.appendChild(navBarView());
    $historyContainer.appendChild($historyContent);

    await fetchOilHistory($historyContainer);

    return $historyContainer;
}

const historyContentView = () => {
    const $historyContent = document.createElement("div");

    return $historyContent;
}

const historyRowView = (chargeDate, brand, gasStationName, gasType, recordGasAmount, refuelingPrice, savingPrice) => {
    const $historyRow = document.createElement("div");
    $historyRow.innerHTML = getHistoryRowTemplate(
        chargeDate, brand, gasStationName, gasType, recordGasAmount, refuelingPrice, savingPrice
    );

    return $historyRow;
}

export { historyView, historyContentView, historyRowView }