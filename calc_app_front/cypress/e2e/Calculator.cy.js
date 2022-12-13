/// <reference types="cypress" />

describe('example to-do app', () => {
    beforeEach(() => {
        cy.visit('http://localhost:3000/')
    })

    it('displays a title and sub-title', () => {
        cy.get('.title').contains("🔫 Calculating Gun")
        cy.get('.sub-title').contains("A calculator with no limits")
    })

    it("should get the page content", () => {
        cy.get("#_calculate_page").should("exist")
        cy.get("[action=calculate]").should("exist")
    })

    it("On success things will work fine", () => {
        
        cy.get("[placeholder=Operand one]").type("20")
        cy.get("[placeholder=Operand two]").type("2")
        
        
        cy.get("[action=calculate]").click()
        
        cy.get("#SuccessMessage").should("exist")
        cy.get("#SuccessMessage").should("include.text", "Sucessfully")
    })

    it('can get operand fields', () => {
        cy.contains('Operand One ')
            .parent()
            .find('input[type=text]')

        cy.contains('Operand Two ')
            .parents()
            .should('have.class', 'input')
    })

    context('with a number enter', () => {
        beforeEach(() => {
            cy.contains('')
                .parent()
                .find('input[type=text]')
                .contains(0)
    })

    })
})
