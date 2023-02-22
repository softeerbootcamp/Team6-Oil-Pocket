import { navBarView } from "../../navbar/view.js";
import { eventToChartLegend } from "./event.js";
import { fetchChart } from "./fetch.js";
import { makeChart } from "./helperFunction.js";
import { getChartTemplate } from "./template.js";

let userOilArray = [100_000, 220_000, 300_000, 290_000, 100_000, 80_000, 85_000, 80_000, 70_000, 105_000];
let commonOilArray = [150_000, 120_000, 280_000, 190_000, 130_000, 90_000, 89_000, 73_000, 86_000, 90_000];
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