export const toCapitalCase = (text: string) => {
    if (text.length === 0) return text
    if (text.length === 1) return text.toUpperCase()
    return text[0].toUpperCase() + text.slice(1).toLowerCase()
}