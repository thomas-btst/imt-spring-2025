# TODO
- [ ] SG : Compléter le CRUD : 
> Pouvoir créer un match (requête POST) 
> Pouvoir mettre à jour le statut d'un match (requête PUT) 
> Supprimer un match (requête DELETE)
- [X] CS = Rechercher un match (requête GET) selon différents critères
- [x] TB = Stocker les utilisateurs en base de données (JdbcUserDetailsManager) => Spring security
- [x] TB = Permettre d'enregistrer des utilisateurs (par API ou par formulaire) => Spring security
- [X] CS = Rajouter un HealthIndicator -> KO si aucun match n'a été joué depuis 1 mois => Actuator
- [ ] TB = Ajouter des contraintes sur le format des données (ex: numéro de joueur, nombre de joueur par équipe, nombre de rounds par match, …) => Spring validation (JSR 303)
- [ ] SG = Logguer les entrants et sortants de l'API sans surcharger les contrôleurs => AOP
- [ ] Ajouter documentation + DTO + Mappers

# A voir
- [ ] Rajouter un champ date à Match => Data

# Idées oubliées
- [ ] Créer en masse des matchs :
> l'api répond un rapport (ajout ok/ko, date, nb evénements, équipe gagnante, mvp...)
> si la création du rapport est ko, les matchs ne doivent pas être enregistrés
> si un match ne peut pas être enregistré, les autres doivent l'être quand même
> il ne faut pas enregistrer de doublon
- [ ] Afficher un formulaire de login pour remplacer l'authentification Basic => Spring security
- [ ] Rajouter des métriques sur les matchs finis lors de leur enregistrement (ou qu'ils passent au statut TERMINE)
