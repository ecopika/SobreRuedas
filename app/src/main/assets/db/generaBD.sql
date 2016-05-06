CREATE TABLE IF NOT EXISTS personatge_ES (ID INT PRIMARY KEY NOT NULL,NOM TEXT NOT NULL,EDAT INT,COLOR_P TEXT,MENJAR_P TEXT,  HOBBY TEXT, IDOL TEXT, ANIMAL TEXT, PASIONS TEXT, HABITS TEXT,NOSOPORTA TEXT,FRASE TEXT,SOMNI TEXT,IMATGE TEXT,GIF TEXT, UNAVIDA TEXT, DUESVIDES TEXT);
CREATE TABLE IF NOT EXISTS mapa (ID INT PRIMARY KEY NOT NULL, INICIX FLOAT, INICIY FLOAT, NOMIMG TEXT);
CREATE TABLE IF NOT EXISTS obstacle (ID INT PRIMARY KEY NOT NULL, POS INT, NOMIMGSOL TEXT, MAP INT,FOREIGN KEY(MAP) REFERENCES mapa(ID) );
CREATE TABLE IF NOT EXISTS resposta (ID TEXT PRIMARY KEY NOT NULL, NOMIMGRES TEXT, OBSTACLE INT,FOREIGN KEY(OBSTACLE) REFERENCES obstacle(ID) );
CREATE TABLE IF NOT EXISTS objectesmapa (ID INT PRIMARY KEY NOT NULL,IMATGE TEXT, PROFUNDITAT INT, X FLOAT, Y FLOAT);
CREATE TABLE IF NOT EXISTS perfil(ID INT PRIMARY KEY NOT NULL, NOMPERSONATGE TEXT, DIFICULAT INT, PUNTUACIO INT);
CREATE TABLE IF NOT EXISTS mapa_object(IDMAPA INT NOT NULL, IDOBJECTE INT NOT NULL,PRIMARY KEY (IDMAPA, IDOBJECTE),FOREIGN KEY(IDMAPA) REFERENCES mapa(ID),FOREIGN KEY(IDOBJECTE) REFERENCES objectesmapa(ID));
INSERT INTO personatge_ES (ID,NOM,EDAT,COLOR_P,MENJAR_P,HOBBY,IDOL,PASIONS,HABITS,NOSOPORTA,FRASE,SOMNI,IMATGE,GIF,UNAVIDA, DUESVIDES) VALUES (0,'Núria',10,'azul','Lasaña','Baloncesto','Mujer Maravilla','Pintar','leer comics de mortadelo y filemon','payasos','no hay nada imposible para mi','llegar a ser una gran politica y cambiar las cosas','nuriafotograma','movnuria.gif',"unavida","duesvides");
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (0,"noia1",43,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (1,"noia2",44,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (2,"noia3",45,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (3,"noia4",46,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (4,"noi1",47,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (5,"noi2",48,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (6,"noi3",49,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (7,"noi4",50,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (8,"botiga1",30,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (9,"botiga2",31,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (10,"botiga3",32,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (11,"arbre1",33,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (12,"arbre2",34,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (13,"arbre3",35,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (14,"font",36,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (15,"gat1",40,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (16,"gat2",41,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (17,"gat3",42,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (18,"casa1",37,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (19,"casa2",38,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (20,"barco1",0,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (21,"barco2",1,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (22,"paperera",37,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (23,"cochedreta2",20,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (24,"cochedreta3",21,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (25,"cochedreta4",22,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (26,"cocheesquerra2",10,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (27,"cocheesquerra3",11,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (28,"cocheesquerra4",12,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (29,"cocheesquerra5",13,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (30,"motoesq",14,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (31,"motoesquerra2",15,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (32,"motodret",23,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (33,"motodreta2",24,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (34,"motodreta3",25,0.0,0.0);
INSERT INTO objectesmapa (ID,IMATGE,PROFUNDITAT,X,Y) VALUES (35,"bus",26,0.0,0.0);
INSERT INTO mapa (ID, INICIX, INICIY,NOMIMG)VALUES (0,0.0,0.0,"mapbuit1");
INSERT INTO obstacle (ID, POS, NOMIMGSOL, MAP)VALUES (0,0,"sol1",0);
INSERT INTO resposta (ID,NOMIMGRES,OBSTACLE)VALUES ("rampa","rampa",0);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (0,11);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (0,0);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (0,10);
INSERT INTO mapa_object(IDMAPA, IDOBJECTE) VALUES (0,21);