# billyServices

To start project locally:

1. Clone repositroy https://github.com/SeferAlen/billyServices and pull/checkout branch localhost
2. Open project in IDE (recommended Intellij IDEA) and configure POSTGRESQL database (host, username, password)
3. Start app (port 9090)
4. Admin user will be created at app start: username: Admin, password: adminPassword

5. Use postman to create new users: POST localhost:9090/users , headers: "Content-Type: 'application/json', 
                                                                          Accept: 'application/json'"
and body example: "{
	"billyUser":{
		"first_name":"Jerry",
		"last_name":"theMouse",
		"address":"New York",
		"phone":"0629393211"
	},
	"login":{
		"username":"jerryTheMouse",
		"password":"jerryTheMouse"
	}
}
"

5. Use postman to Login as Admin: POST localhost:9090/login , headers: "Content-Type: 'application/json', 
                                                                        Accept: 'application/json'"
and body "{
	"username":"Admin",
	"password":"adminPassword"
}"

6. With received token create bills for users: POST localhost:9090/bills , headers: "Content-Type: 'application/json', 
                                                                                     Accept: 'application/json',
                                                                                     Authorization: Bearer: {receivedToken}"
and body "{
	"bill":{
		"total":"13.00",
		"date":"2020-2"
	},
	"username":"jerryTheMouse"
}" 

P.S: watch out about Authorization header correct syntax

7. clone repository https://github.com/SeferAlen/BillyApp
8. Open project in IDE (recommended VisualStudioCode) and pull/checkout branch localhost
9. In terminal in BillyApp project type ng serve (node must be installed)
