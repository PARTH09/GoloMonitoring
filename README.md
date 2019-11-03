# GoloMonitoring
Golo Monitoring spring boot api 

http://localhost:8080/golomonitor

Rest Api has 3 EndPoints And Swagger
- start
- stop
- statistics
- v2/api-docs


1. Start Monitoring -
      POST  http://localhost:8080/golomonitor/start
      
      Json input for hostName and interval.
      {
	      "hostName":"https://api.test.paysafe.com/accountmanagement/monitor",
	      "interval":"5000"
      }
 
2. Stop Monitoring - 
       GET  http://localhost:8080/golomonitor/stop
       
       
3. To show overview 
      GET http://localhost:8080/golomonitor/statistics
