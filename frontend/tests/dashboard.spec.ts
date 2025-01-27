import {expect, Locator, Page, test} from '@playwright/test';
import {LECTOR_PORTAL_APP_PATHS} from "@/app/constants/paths";

const LOGIN = { name: 'leon@doom.at', pw: 'escapeDoom' }

test.beforeEach(async ({page}) => {
    await page.goto(LECTOR_PORTAL_APP_PATHS.LOGIN)

    // Submits form
    await page.getByLabel('Email Address').fill(LOGIN.name);
    await page.getByLabel('Password').fill(LOGIN.pw)
    await page.getByRole("button", {name: "Log In"}).click()

    // Wait until the page receives the cookies.
    //
    // Sometimes login flow sets cookies in the process of several redirects.
    // Wait for the final URL to ensure that the cookies are actually set.
    await page.waitForURL(LECTOR_PORTAL_APP_PATHS.DASHBOARD);
    // await page.goto(LECTOR_PORTAL_APP_PATHS.DASHBOARD)
})

async function openEscapeRoom(card: Locator, statusLed: Locator) {
    await card.getByRole('button', {name: 'Open'}).click()
    await expect(statusLed).toHaveCSS('color', 'rgb(255, 255, 0)')
}

async function startEscapeRoom(card: Locator, statusLed: Locator) {
    await card.getByRole('button', {name: 'Start'}).click()
    await expect(statusLed).toHaveCSS('color', 'rgb(0, 255, 0)')
}

async function stopEscapeRoom(card: Locator, statusLed: Locator) {
    await card.getByRole('button', {name: 'Close'}).click()
    await expect(statusLed).toHaveCSS('color', 'rgb(255, 0, 0)')
}

test.describe('Dashboard', () => {

    test('should open escape room that is closed', async ({page}) => {
        const {card, statusLed} = await getFirstEscapeRoom(page)

        await expect(statusLed).toHaveCSS('color', 'rgb(255, 0, 0)')

        await mockAPIRequest(page)

        await openEscapeRoom(card, statusLed);
    })

    test('should start escape room that is open', async ({page}) => {
        const {card, statusLed} = await getFirstEscapeRoom(page)

        await mockAPIRequest(page)

        await openEscapeRoom(card, statusLed);

        await startEscapeRoom(card, statusLed);
    })

    test('should close escape room that is started', async ({page}) => {
        const {card, statusLed} = await getFirstEscapeRoom(page)

        await mockAPIRequest(page)

        await openEscapeRoom(card, statusLed)

        await startEscapeRoom(card, statusLed)

        await stopEscapeRoom(card, statusLed);
    })

})

const getFirstEscapeRoom = async (page: Page) => {
    const firstRoomCard = page.locator('.MuiCard-root').first()

    firstRoomCard.count().then(value => console.log(value))

    const statusLed = firstRoomCard.locator('#status_led').first();

    return {card: firstRoomCard, statusLed: statusLed}
}

const mockAPIRequest = async (page: Page) => {
    const baseRequestURL = '*/**/api/v1/portal-escape-room'
    await page.route(`${baseRequestURL}/openEscapeRoom/1`, async (route) => {
        await route.fulfill({
            status: 200,
            contentType: 'application/json',
            body: '123'
        })
    })

    await page.route(`${baseRequestURL}/startEscapeRoom/1/90`, async (route) => {
        await route.fulfill({
            status: 200,
            contentType: 'application/json',
            body: 'Stopped EscapeRoom with ID'
        })
    })

    await page.route(`${baseRequestURL}/stopEscapeRoom/1`, async (route) => {
        await route.fulfill({
            status: 200,
            contentType: 'application/json',
            body: 'Stopped EscapeRoom with ID'
        })
    })

}

