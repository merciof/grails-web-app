package minha.turma.grails.app

import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(User) {
            def output = [:]
            output['id'] = it.id
            output['name'] = it.name
            output['username'] = it.username

            return output
        }

        JSON.registerObjectMarshaller(Student) {
            def output = [:]
            output['id'] = it.id
            output['name'] = it.name
            output['username'] = it.username
            output['feeling'] = it?.feeling?.name
            output['presence'] = it?.presence
            output['schoolClass'] = ["id": it?.schoolClass?.id, "name": it?.schoolClass?.name]

            return output
        }

        JSON.registerObjectMarshaller(Lecture) {
            def output = [:]
            output['id'] = it.id
            output['date'] = it.date
            output['schoolClass'] = ["id": it?.schoolClass?.id, "name": it?.schoolClass?.name]
            output['subject'] = ["id": it?.subject?.id, "name": it?.subject?.name]
            output['topic'] = it.topic
            output['owner'] = ["id": it?.owner?.id, "name": it?.owner?.name]
            output['isOpen'] = it.isOpen

            return output
        }

        JSON.registerObjectMarshaller(Presence) {
            def output = [:]
            output['id'] = it.id
            output['student'] = ["id": it?.student?.id, "name": it?.student?.name]
            output['lecture'] = ["id": it?.lecture?.id, "subject": it?.lecture?.subject]

            return output
        }

        JSON.registerObjectMarshaller(Message) {
            def output = [:]
            output['id'] = it.id
            output['subject'] = it.subject
            output['content'] = it.content
            output['schoolClass'] = ["id": it?.schoolClass?.id, "name": it?.schoolClass?.name]
            output['owner'] = ["id": it?.owner?.id, "name": it?.owner?.name]
            output['date'] = it.date

            return output
        }

        JSON.registerObjectMarshaller(Confidence) {
            def output = [:]
            output['id'] = it.id
            output['topic'] = it.topic
            output['status'] = it.status.name
            output['student'] = ["id": it?.student?.id, "name": it?.student?.name]
            output['subject'] = ["id": it?.subject?.id, "name": it?.subject?.name]

            return output
        }

        JSON.registerObjectMarshaller(Answer) {
            def output = [:]
            output['id'] = it.id
            output['student'] = ["id": it?.student?.id, "name": it?.student?.name]
            output['quiz'] = ["id": it?.quiz?.id, "statement": it?.quiz?.statement, "correct": it?.quiz?.correct]
            output['choice'] = it.choice
            return output
        }

        // Create application roles
        Role adminRole = new Role('ROLE_ADMIN').save()
        new Role('ROLE_STUDENT').save()

        // Create test users
        Professor admin = new Professor(name: 'Pedro Souza', username: 'admin', password: 'admin', authorities: [adminRole]).save()

        // Associate users to roles
        new UserRole(user: admin, role: adminRole).save()

        Subject sub = new Subject(name: "Biologia")
                    .addToTopics("Introdução à Biologia")
                    .addToTopics("Química da vida")
                    .addToTopics("Água, ácidos e bases")
                    .addToTopics("Propriedades do carbono")
                    .addToTopics("Macromoléculas")
                    .addToTopics("Energia e enzimas")
                    .addToTopics("Estrutura celular")
                    .addToTopics("Membranas e transporte")
                    .addToTopics("Respiração celular")
                    .addToTopics("Fotossíntese")
                    .addToTopics("Sinalização celular")
                    .addToTopics("Divisão celular")
                    .addToTopics("Genética clássica e genética molecular")
                    .addToTopics("DNA como o material genético")
                    .addToTopics("Dogma central (DNA a RNA a proteína)")
                    .addToTopics("Regulação gênica")
                    .addToTopics("Biotecnologia")
                    .addToTopics("Biologia do desenvolvimento")
                    .addToTopics("Bactérias e arqueas")
                    .addToTopics("Vírus")
                    .addToTopics("A evolução e a árvore da vida")
                    .addToTopics("História da vida na Terra")
                    .addToTopics("Ecologia")
                    .addToTopics("Biodiversidade e conservação")
                    .addToTopics("Biologia Comportamental")
                    .addToTopics("Princípios da fisiologia")
                    .addToTopics("Biologia humana")
                    .addToTopics("Biologia vegetal")
                    .addToTopics("Curso intensivo: Biologia e Ecologia")
                    .save()

        new Quiz(statement: "O Pica-Pau pode dar quantas bicadas por minuto em uma árvore?", subject: sub, topic: "Biodiversidade e conservação" , alternatives: ["10", "100", "1000", "10000", "NDA"], correct: "B", owner: admin, isOpen: false).save()
        new Quiz(statement: "Quantas vezes o Beija-Flor bate as asas por segundo?", subject: sub, topic: "Biodiversidade e conservação" , alternatives: ["17", "5", "90", "1000", "105"], correct: "C", owner: admin, , isOpen: false).save()
        new Quiz(statement: "O filhote de um boi recebe 3 nomes diferentes. Quais são eles?", subject: sub, topic: "Biodiversidade e conservação" , alternatives: ["leitão, novilho ou pônei",
                "boizinho, bezerro ou mini boi", "leitão, bezerro ou vitelo", "vitelo, bezerro ou alcatra", "NDA"], correct: "D", owner: admin, , isOpen: false).save()

        
        
        
        /*new Subject(name: "Matemática").save()
        new Subject(name: "Português").save()
        new Subject(name: "História").save()
        new Subject(name: "Geografia").save()
        new Subject(name: "Artes").save()
        new Subject(name: "Inglês").save()
        new Subject(name: "Ciências").save()*/

    }
    def destroy = {
    }
}
