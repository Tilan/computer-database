Eric Bernelin
Christophe Touret 

------------------------------------------------------------------
CORRECTION+CODE REVIEW:
------------------------------------------------------------------

-------------------------
Expérience utilisateur:
-------------------------

Je lance: JDBC exception: access denied for admin@localhost > Vous n'avez pas créé d'utilisateur admin et mot de passe admin dans la base de données, j'ai du modifier votre context.xml, attention la prochaine fois.

Allez, ca y est on arrive sur la page:

Delete computer: ne fonctionne pas si mon navigateur est recent (Firefox), il me dit "please match the requested format" sur la introduced date. C'est un probleme dans votre jsp! Le pattern que vous avez defini pour les champs date sont des expressions regulieres (regex) et non pas un descripteur de dateFormat.
Pour decrire une date dans votre format, il aurait fallu mettre ceci dans pattern:
(19[6-9][0-9]|20[0-9][0-9])\-(0?[1-9]|1[12])\-(0?[1-9]|[1-2][0-9]|3[01])



Orderby: ca marche aussi, mais si je veux un ordinateur "ZZ", recliquer sur "Computer Name" devrait pouvoir afficher l'ordre décroissant, ca aurait été sympa d'implémenter ça.

Recherche: ca marche, mais j'aurais aimé garder le texte de ma recherche dans le champ, au lieu de remettre le placeholder. Idem, quand je lance une recherche, et que je fais un orderby ensuite, je perds la recherche...

Ajout d'ordinateur: Ca marche, mais meme probleme que la suppression et l'edit pour le pattern avec un navigateur recent (chrome/ff)

Edit d'ordinateur:
Idem, mais bravo pour le parsage des dates et l'affichage en parse.

-------------------------
Le code:
-------------------------
-Commentaires: tres bien!

-Protection des jsp: bien sauf le dossier include qui devrait aussi etre dans le web-inf
	
-Utilisation des builder: oui

-Utilisation des enums pour les singletons: yes!


-Services: RAS

-Dao: 

-Domain: RAS

-JSP: Cool pour l'utilisation des balises pour afficher vos images, ainsi que pour les tests

Comme d'autres, votre pagination est assez lourde (c'est normal) et le calcul d'URL est nécessairement long. On devrait sortir le traitement de la JSP et en faire une taglib (meilleure cohésion!)

Idem pour les messages d'erreur.

Bien d'avoir exporté les scripts dans un fichier js, et bonne utilisation de jquery

Le code est plus propre que dans le code java, même si vous avez quand même pas mal de bordel là dedans!

-------------------------
Bilan: 
-------------------------
Très bon projet, à part les quelques erreurs que vous avez fait (le context en double, les problèmes d'edit). Vous avez bossé sérieusement et côté expérience utilisateur je suis vraiment satisfait.
Quand on ouvre la boite, on trouve un code qui est difficile à lire, et parfois peu cohérent (setter deux fois les memes attributs). N'hésitez pas à prendre le temps d'aérer ca un peu mieux à l'avenir, car sur un projet de 200k lignes, ca ferait mal!
Je chipote, mais quand je chipote c'est bon signe.
