package banking.command;

import banking.exception.AppException;
import banking.model.Model;
import banking.view.IPage;

public interface ICommand {

    IPage execute(Model model) throws AppException;

}
