// Returns the common highcharts options
function getDefaultOptions()
{
	var options = {
	   colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
	   tooltip: {
	       enabled:true
	   },
	   chart: {
	      backgroundColor: {
		 linearGradient: [0, 0, 500, 500],
		 stops: [
		    [0, 'rgb(255, 255, 255)'],
		    [1, 'rgb(240, 240, 255)']
		 ]
	      },
	      borderWidth: 2,
	      plotBackgroundColor: 'rgba(255, 255, 255, .9)',
	      plotShadow: true,
	      plotBorderWidth: 1
	   },
	   title: {
	      style: {
		 color: '#000',
		 font: 'bold 16px "Trebuchet MS", Verdana, sans-serif'
	      }
	   },
	   subtitle: {
	      style: {
		 color: '#666666',
		 font: 'bold 12px "Trebuchet MS", Verdana, sans-serif'
	      }
	   },
	   xAxis: {
	      gridLineWidth: 1,
	      lineColor: '#000',
	      tickColor: '#000',
	      labels: {
		 style: {
		    color: '#000',
		    font: '11px Trebuchet MS, Verdana, sans-serif'
		     }
	      },
	      title: {
   		 style: {
		    color: '#333',
		    fontWeight: 'bold',
		    fontSize: '12px',
		    fontFamily: 'Trebuchet MS, Verdana, sans-serif'

		 }
	      }
	   },
	   yAxis: {
              min:0,
	      minorTickInterval: 'auto',
	      lineColor: '#000',
	      lineWidth: 1,
	      tickWidth: 1,
	      tickColor: '#000',
	      labels: {
		 style: {
		    color: '#000',
		    font: '11px Trebuchet MS, Verdana, sans-serif'
		 }
	      },
	      title: {
		 style: {
		    color: '#333',
		    fontWeight: 'bold',
		    fontSize: '12px',
		    fontFamily: 'Trebuchet MS, Verdana, sans-serif'
		 }
	      }
	   },
	   legend: {
	      enabled:true,
	      itemStyle: {
		 font: '9pt Trebuchet MS, Verdana, sans-serif',
		 color: 'black'

	      },
	      itemHoverStyle: {
		 color: '#039'
	      },
	      itemHiddenStyle: {
		 color: 'gray'
	      }
	   },
	   labels: {
	      shared:true,
	      style: {
		 color: '#99b'
	      }
	   }
	};

    return options;	
}

// Returns the common highchart options for line, bar graphs
function getDefaultLineChartOptions()
{
    
    var options = getDefaultOptions();
    
    var line_options = jQuery.extend(true, {}, options);
    line_options.series = [{}];
    line_options.plotOptions =  {
            area: {
                fillColor: {
                    linearGradient: {
                        x1: 0,
                        y1: 0,
                        x2: 0,
                        y2: 1
                    },
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, 'rgba(2,0,0,0)']
                    ]
                },
                lineWidth: 1,
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: true,
                            radius: 5
                        }
                    }
                },
                shadow: false,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                }
            }
        };
    
    return line_options;
}

// Returns the chart options for pie graphs
function getDefaultPieChartOptions()
{
    var options = getDefaultOptions();

    var pie_options = jQuery.extend(true, {}, options);
    pie_options.series = [{type: 'pie'}];
    pie_options.tooltip = {
                    enabled:true,
                    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
                    percentageDecimals: 1
                };
    pie_options.plotOptions = {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            color: '#000000',
                            connectorColor: '#000000',
                            formatter: function() {
                                return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(2) +' %';
                            }
                        }
                    }
                };
    
    return pie_options;

}