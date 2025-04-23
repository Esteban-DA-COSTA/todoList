type Nullable<T> = T | null | undefined
export namespace models {
    class TodoTask {
        constructor(id: number, title: string, completed?: boolean);
        get id(): number;
        get title(): string;
        set title(value: string);
        get completed(): boolean;
        set completed(value: boolean);

        toString(): string;


        static get Companion(): {
        };
    }
}
export as namespace com_todolist_shared;