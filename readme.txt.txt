Prije pokretanja treba inicirati Postgres bazu podataka.
Na localhostu kreirati novu Postgres bazu podataka pod imenom vhs-lab.
Zatim u resources/application.properties podesiti slijedeće konekcijske stringove prema vlastitoj bazi:
	spring.datasource.url=
	spring.datasource.username=
	spring.datasource.password=
Nakon toga možete uspješno pokrenuti aplikaciju i testirati je sa Postman kolekcijom koja se nalazi u ovom repozitoriju.
