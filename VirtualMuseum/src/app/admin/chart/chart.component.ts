import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  constructor() { }
  private values:Map<string,number>;
  ngOnInit(): void {
    var keysArray;
    var valuesArray;
    fetch("http://localhost:8080/admin/aktivniPoSatima").then((response: { json: () => any; })=>{
      return response.json()
    }
  ).then(responseData=>{
    this.values= responseData;
     
    keysArray = Object.keys(this.values);
    valuesArray = Object.values(this.values);

    
    const myChart = new Chart(
      "chart",
      {
        type: 'line',
        data: {
            labels: keysArray,
            datasets: [{
                label: 'Active users in last 24 hours',
                data: valuesArray
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
    
  })
    
  
 
  }

}
