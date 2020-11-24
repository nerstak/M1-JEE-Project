package utils.database;

import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_UPDATE_COMMENTS;

public class CommentsDataServices extends DataServices {
    public CommentsDataServices(String login, String pwd, String connectionUrl) {
        super(login, pwd, connectionUrl);
    }

    /**
     * update the comments table
     * @param commentsId, the id of the comment
     * @param commentStudent, the student's comment
     * @param commentsSupervisor, the tutor's comment
     * @return the number of row affected
     */
    public int updateComments(String commentsId, String commentStudent, String commentsSupervisor) {
        try {
            ps = con.prepareStatement(DB_UPDATE_COMMENTS);
            ps.setString(1, commentsSupervisor);
            ps.setString(2, commentStudent);
            ps.setObject(3, commentsId, Types.OTHER);

            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
}
