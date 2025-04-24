type Nullable<T> = T | null | undefined
export namespace models {
    class TodoTask {
        constructor(id: number, title: string, description: string, completed?: boolean, dueDate?: Nullable<string>);
        get id(): number;
        get title(): string;
        set title(value: string);
        get description(): string;
        set description(value: string);
        get completed(): boolean;
        set completed(value: boolean);
        get dueDate(): Nullable<string>;
        set dueDate(value: Nullable<string>);

        toString(): string;


        static get Companion(): {
        };
    }
}
export as namespace com_todolist_shared;