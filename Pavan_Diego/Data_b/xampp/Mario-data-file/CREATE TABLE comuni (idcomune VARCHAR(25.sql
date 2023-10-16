CREATE TABLE comuni (idcomune VARCHAR(255) , nome VARCHAR(255) ,  abitanti int, prefisso INT, idprovincia VARCHAR(255));

CREATE TABLE province (idprovincia VARCHAR(255) ,  idregione  VARCHAR(255), nome VARCHAR(255));

CREATE TABLE regioni (autonoma int,  idregione  VARCHAR(255), nome VARCHAR(255));




INSERET INTO comuni (idcomune, nome, abitanti, prefisso, idprovincia) VALUES ("L736","Venezia",4906000,041,"ITA-34");


INSERT INTO comuni (idcomune, nome, abitanti, prefisso, idprovincia)
VALUES
  ('A001', 'Padova', 210000, '049', 'ITA-34'),
  ('A002', 'Verona', 250000, '045', 'ITA-34'),
  ('A003', 'Treviso', 80000, '042', 'ITA-34'),
  ('A004', 'Vicenza', 120000, '044', 'ITA-34'),
  ('A005', 'Belluno', 35000, '043', 'ITA-34'),
  ('A006', 'Rovigo', 50000, '042', 'ITA-34'),
  ('A007', 'Chioggia', 20000, '041', 'ITA-34'),
  ('A008', 'Mestre', 150000, '041', 'ITA-34'),
  ('A009', 'Cittadella', 25000, '049', 'ITA-34'),
  ('A010', 'Montagnana', 18000, '042', 'ITA-34');


INSERT INTO province (idprovincia, idregione, nome)
VALUES
  ('ITA-34-001', 'ITA-34', 'Belluno'),
  ('ITA-34-002', 'ITA-34', 'Padova'),
  ('ITA-34-003', 'ITA-34', 'Rovigo'),
  ('ITA-34-004', 'ITA-34', 'Treviso'),
  ('ITA-34-005', 'ITA-34', 'Venezia'),
  ('ITA-34-006', 'ITA-34', 'Verona'),
  ('ITA-34-007', 'ITA-34', 'Vicenza'),
  ('ITA-34-008', 'ITA-34', 'Chioggia'),
  ('ITA-34-009', 'ITA-34', 'Mestre'),
  ('ITA-34-010', 'ITA-34', 'Cittadella');


  
INSERT INTO regioni (autonoma, idregione, nome)
VALUES
  (1, 'ITA-01', 'Piemonte'),
  (1, 'ITA-02', 'Valle dAosta'),
  (0, 'ITA-03', 'Lombardia'),
  (0, 'ITA-04', 'Trentino-Alto Adige'),
  (1, 'ITA-05', 'Veneto'),
  (0, 'ITA-06', 'Friuli-Venezia Giulia'),
  (1, 'ITA-07', 'Liguria'),
  (0, 'ITA-08', 'Emilia-Romagna'),
  (0, 'ITA-09', 'Toscana'),
  (1, 'ITA-10', 'Umbria');