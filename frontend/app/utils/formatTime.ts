export const formatTime = (time: string | undefined ) => {
    if( typeof time === 'undefined'){
        return new Date().toISOString().substring(11, 19);
    }

    return new Date(time).toISOString().substring(11, 19);
}

export const formatTimeInt = (time: number) => {
    return new Date(time * 1000).toISOString().substring(11, 19);
}