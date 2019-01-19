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

            return output
        }

        JSON.registerObjectMarshaller(Message) {
            def output = [:]
            output['id'] = it.id
            output['subject'] = it.subject
            output['content'] = it.content
            output['schoolClass'] = ["id": it?.schoolClass?.id, "name": it?.schoolClass?.name]
            output['owner'] = ["id": it?.owner?.id, "name": it?.owner?.name]

            return output
        }

        // Create application roles
        Role adminRole = new Role('ROLE_ADMIN').save()

        // Create test users
        Professor admin = new Professor(name: 'Admin', username: 'admin', password: 'admin', authorities: [adminRole]).save()

        // Associate users to roles
        new UserRole(user: admin, role: adminRole).save()

        new Quiz(statement: "Quem descobriu o Brasil", alternatives: ["Pedro", "Aecia", "Lula", "Dilma"], correct: 0, owner: admin).save()
        new Quiz(statement: "Quem é o atual presidente do Brasil?", alternatives: ["Dilma", "Aecio", "Bolsonaro", "Lula"], correct: 0, owner: admin).save()
        new Quiz(statement: "1+1?", alternatives: ["1", "2", "3", "4"], correct: 0, owner: admin).save()
        new Quiz(statement: "Qual a cor do céu?", alternatives: ["Azul", "Amarelo", "Vermelho", "Laranja"], correct: 0, owner: admin).save()
        new Quiz(statement: "Coletivo de abelha", alternatives: ["Enxame", "Alcateia", "Gado", "Cardume"], correct: 0, owner: admin).save()

    }
    def destroy = {
    }
}
