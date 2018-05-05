# BDAD HW9 B

```scala
val accounts = sc.textFile("loudacre/accounts").map(line => line.split(',')).keyBy(items => items(8))
val cleaned = accounts.mapValues(items => items(4) + "," + items(3)).groupByKey()
val sorted = cleaned.sortByKey().mapValues(v => v.toArray.mkString("\n")).map{ case (k,v) => "---" + k + "\n" + v  + "\n"}
```
```scala

scala> accounts.take(10).foreach(println)
(94660,[Ljava.lang.String;@682c3475)
(94171,[Ljava.lang.String;@4d01c493)
(94479,[Ljava.lang.String;@2f10b664)
(94444,[Ljava.lang.String;@7236775f)
(94872,[Ljava.lang.String;@55d7388d)
(94264,[Ljava.lang.String;@24c1dd02)
(94508,[Ljava.lang.String;@3394c128)
(94469,[Ljava.lang.String;@6b15438c)
(94312,[Ljava.lang.String;@4105d340)
(94577,[Ljava.lang.String;@2865ed56)

```
```scala

scala> cleaned.take(2).foreach(println)
(95461,CompactBuffer(Stewart,Monique, Boston,Shana, Bradford,Robert, Evans,Michael, Walker,James, Dorazio,Charles, Malec,Derrick, Toney,Carolyn, Wicker,Eileen, Strange,Michael, Benally,Josephine, Sanchez,Steven, Johnson,Rebecca, Hollier,Harold, Rush,Eddie, Copley,Elizabeth, Cowles,Terry))
(91933,CompactBuffer(Moore,Judy, Larsen,Kevin, Maziarz,Ronald, Wilson,Linda, Lindner,Irene, Humbert,Andrew, Rivera,Chuck, Guerrier,Diana, Miller,Philip, Johnson,Carmine, Maxey,Beulah, Albaugh,Jean, Freeman,Luis, Surface,Carolyn, Castleberry,Otis, Randolph,Anne))

```
```scala
scala> sorted.take(5).foreach(println)
---85000
Mackenzie,James
Chamberlain,Robert
Cunningham,Richard
Allen,Harvey
Prinz,Daniel
Pascale,Robert
Brookes,Donna
Sewell,Bailey
Marin,Daniel

---85001
Mendelsohn,Frances
Watson,Mary
Brookover,Donald
Hathaway,Brandon
Leonard,Crystal
Moran,Carrie
Kirksey,Marie
Wilkins,Timothy
Snyder,Joseph
Flores,Delbert
Eakes,Gail
Daniels,Bert
Carpenter,Vincent
Lance,Issac
Barnes,Vesta
Fiore,Eva
Tucker,Keith
Medford,Danielle
Spell,Norman
Soto,Shelley
Frantz,Kathy

---85002
Whitney,Ruby
Payne,Jessica
Stewart,Bryant
Jones,Jose
Robinson,Wesley
Perry,David
James,Marianne
Holiman,Nancy
Roman,Allen
Manus,Donna
Reed,Nancy
Baird,Estella
Gilbert,James
McKay,David
Clark,Laura
Horn,John

---85003
Gibson,Catherine
Thies,Lindsey
Martin,Mark
Dvorak,Kevin
Wisniewski,Virginia
Ross,Vivian
Tabor,Harry
Strickland,Kyle

---85004
Shirley,Joseph
White,Sandra
Stern,Timothy
Johnson,Dominic
Dewitt,Mary
Carpenter,Matthew
Ball,Annie
Pate,Kathleen
Lish,Carrie
Kitts,Mary
Viola,Kevin
Meadows,Tonya
Royalty,Sherry
Collins,Greg
```
