Projet Active Java
========================

## _But :_

Application permettant de choisir entre la gestion de clients et de prospects :

* **Gestion des clients :**  permet de créer et mettre à jour des clients en relation avec la table **Client** de la base de données. 

* **Gestion des prospects :**  permet de créer et mettre à jour des prospects en relation avec la table **Prospect** de la base de données.

## _Principe :_

* Création d'une classe abstraite Societé - Client et Prospect héritent de cette classe
* Affichage des clients et des prospects dans des tableaux, recherche dynamique en fonction de la saisie.
* Utilisation d'écrans de formulaire associés à des contrôles de saisie ( regEx ) lors de la création ou de la mise à jour.
* Récupération des informations existantes et affichage dans le formulaire lors de la mise a jour  
* Création du numéro d'identification d'un prospect par concaténation de l'année de création du prospect suivi d'un numéro incrémenté à chaque création dans la même année.
* Contrôle de la date de prospection, régle de contrôle du chiffre d'affaire et du nombre d'employés.
* Sauvegarder et affichager des 10 meilleurs chiffre d'affaire client.
* Sauvegarder et affichager les prospects intéressés et dont la prospection date de plus de 30 jours.
