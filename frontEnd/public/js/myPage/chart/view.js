import { navBarView } from "../../navbar/view.js";
import { eventToChartLegend } from "./event.js";
import { fetchChart } from "./fetch.js";
import { makeChart } from "./helperFunction.js";
import { getChartTemplate } from "./template.js";

let userOilArray = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
let commonOilArray = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
let monthArray = [];

const chartView = async () => {
    const $chartContainer = document.createElement("section");
    const $chartContent = document.createElement("section");
    $chartContent.classList.add("main");

    $chartContent.innerHTML = getChartTemplate();

    $chartContainer.appendChild(navBarView());
    $chartContainer.appendChild($chartContent);

    await fetchChart();

    makeChart($chartContent, userOilArray, commonOilArray, monthArray);
    eventToChartLegend($chartContent);

    return $chartContainer;
}

export { userOilArray, commonOilArray, monthArray, chartView }