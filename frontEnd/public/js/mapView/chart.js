import { isReleaseMode } from "../common/utils";
import { transStId } from "./ExtraFunctions";

function getChartData(Resultelem){
    const HOST_URL = isReleaseMode() ? 
                        "https://www.oilpocket.kro.kr" :
                        "http://localhost:8080" ;
    $.ajax({
        method:"GET",
        url:`${HOST_URL}/api/v1/gas-station/` + Resultelem.name + "/" + Resultelem.roadName + "/" 
        + Resultelem.buildingNo1 + "/" + transStId(Resultelem.stId) + "/month",
        xhrFields: {
            withCredentials: true
        },
        success:function(response) {
            console.log(response);
            if(Resultelem.hhPrice ==0 && Resultelem.ggPrice ==0 && Resultelem.llPrice ==0){
                ShowErrorMessageAtChartArea();
            }
            else {
                ShowChart(response);
            }
        },
        error:function(request, error){
            ShowErrorMessageAtChartArea();
        }
    });
}

function ShowChart(response){
    const dateLabels = [];
    const PREGasolineData = [];
    const GasolineData = [];
    const DiselData = [];
    const LPGData = [];
    let data;
    const PriceInformation = response.data.details;
    for(var k in PriceInformation){
        if(PriceInformation[k].gasType == 'PREMIUM_GASOLINE'){
            PREGasolineData.push(PriceInformation[k].price);
        }
        else if(PriceInformation[k].gasType == 'GASOLINE'){
            GasolineData.push(PriceInformation[k].price);
        }
        else if(PriceInformation[k].gasType == 'DIESEL'){
            DiselData.push(PriceInformation[k].price);
        }
        else if(PriceInformation[k].gasType == 'LPG'){
            LPGData.push(PriceInformation[k].price);
        }
    }
    var Preflag=0;
    for(var index=0; index<30;index++){
        if(PREGasolineData[index]==0){
            continue;
        }
        else Preflag=1;
    }

    console.log(PriceInformation.length/30);
    if(LPGData.length != 0){
        for(var k =0;k<PriceInformation.length;k+=parseInt(PriceInformation.length/30)){
            dateLabels.push(PriceInformation[k].date.slice(-2));
        }
        data = {
            labels: dateLabels,
            datasets: [
              {
                label: 'LPG',
                backgroundColor: '#008000',
                borderColor: '#008000',
                data: LPGData,
                fill:false,
              }
          ]
        };
    }
    else {
        if(Preflag ==0){
            for(var k =0;k<PriceInformation.length; k+=3){
                dateLabels.push(PriceInformation[k].date.slice(-2));
            }
            data = {
                labels: dateLabels,
                datasets: [
                  {
                      label: '휘발유',
                      backgroundColor: '#93bf85',
                      borderColor: '#93bf85',
                      data: GasolineData,
                      fill:false,
                  },
                  {
                      label: '경유',
                      backgroundColor: '#008000',
                      borderColor: '#008000',
                      data: DiselData,
                      fill:false,
                  }
              ]
            };
        }
        else {
            for(var k =0;k<PriceInformation.length;k+=3){
                dateLabels.push(PriceInformation[k].date.slice(-2));
            }
            data = {
                labels: dateLabels,
                datasets: [
                  {
                      label: '고급유',
                      backgroundColor: '#dbead5',
                      borderColor: '#dbead5',
                      data: PREGasolineData,
                      fill:false,
                  },
                  {
                      label: '휘발유',
                      backgroundColor: '#93bf85',
                      borderColor: '#93bf85',
                      data: GasolineData,
                      fill:false,
                  },
                  {
                      label: '경유',
                      backgroundColor: '#008000',
                      borderColor: '#008000',
                      data: DiselData,
                      fill:false,
                  }
              ]
            };
        }
    }

    const config = {
      type: 'line',
      data: data,
      options: {
        responsive: false,
        pointStyle:false,
        }
    }

    const myChart = new Chart(
      document.getElementById('myChart'),
      config
    )
}


function ShowErrorMessageAtChartArea(){
    if(document.getElementById('myChart')){
        document.getElementById('myChart').remove();
    }
    if(document.getElementById('nocharterr')){
        document.getElementById('nocharterr').remove();
    }
    const ChartArea = document.getElementsByClassName('main__GSTdetail__Contents__Chart');
    // ChartArea[0].innerHTML = "";

    const errorMessage = document.createElement('span');
    errorMessage.id = 'nocharterr';
    errorMessage.innerHTML = '현재 데이터를 준비중입니다.';
    ChartArea[0].append(errorMessage);
}
export {getChartData, ShowChart}