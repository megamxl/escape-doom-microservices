export const formatTime = (time: number) => {
    if (time < 60) return time + " min"
    return Math.floor(time / 60).toString().padStart(2, '0') + ":" + (time % 60).toString().padStart(2, '0') + "h"
}

export const formatDate = (time: number): string => {
    const hours: string = Math.floor(time / (60 * 60)).toString().padStart(2, '0')
    const minutes: string = Math.floor(time / 60).toString().padStart(2, '0')
    const seconds: string = (time % 60).toString().padStart(2, '0')
    return `${hours}:${minutes}:${seconds}`
}