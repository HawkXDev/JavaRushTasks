package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private final Path logDir;
    private final List<LogEntity> logs = new ArrayList<>();
    private final DateFormat formatter = new SimpleDateFormat("d.M.yyyy H:m:s");

    public LogParser(Path logDir) {
        this.logDir = logDir;
        readLogs();
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        return getIpsSet(stream).size();
    }

    private Stream<LogEntity> getFilteredStream(Date after, Date before) {
        Stream<LogEntity> stream = logs.stream();
        return stream.filter(log -> dateBetweenDates(log.getDate(), after, before));
    }

    private boolean dateBetweenDates(Date current, Date after, Date before) {
        if (after == null) {
            after = new Date(0);
        }
        if (before == null) {
            before = new Date(Long.MAX_VALUE);
        }
        return current.after(after) && current.before(before);
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        return getIpsSet(stream);
    }

    private static Set<String> getIpsSet(Stream<LogEntity> stream) {
        return stream.map(LogEntity::getIp).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        return getIpsSet(getFilteredByName(user, stream));
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        return getIpsSet(stream.filter(log -> log.getEvent() == event));
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        return getIpsSet(stream.filter(log -> log.getStatus() == status));
    }

    private void readLogs() {
        try (Stream<Path> stream = Files.walk(logDir)) {
            stream.filter(path -> Files.isRegularFile(path) && path.toString().endsWith(".log"))
                    .forEach((Path path) -> {
                        try {
                            List<String> lines = Files.readAllLines(path);

                            for (String logString : lines) {
                                String[] params = logString.split("\t");
                                if (params.length != 5) {
                                    continue;
                                }

                                String ip = params[0];
                                String user = params[1];
                                Date date = readDate(params[2]);
                                Event event = readEvent(params[3]);
                                Integer taskNumber = readTaskNumber(params[3]);
                                Status status = Status.valueOf(params[4]);

                                logs.add(new LogEntity(ip, user, date, event, taskNumber, status));
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Date readDate(String dateParam) {
        Date date;
        try {
            date = formatter.parse(dateParam);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    private static Event readEvent(String eventParam) {
        String[] split = eventParam.split(" ");
        String eventVal = split[0];
        return Event.valueOf(eventVal);
    }

    private static Integer readTaskNumber(String eventParam) {
        String[] split = eventParam.split(" ");
        if (split.length == 2) {
            return Integer.valueOf(split[1]);
        }
        return null;
    }

    @Override
    public Set<String> getAllUsers() {
        Stream<LogEntity> stream = logs.stream();
        return getUsers(stream);
    }

    private static Set<String> getUsers(Stream<LogEntity> stream) {
        return stream.map(LogEntity::getUsername).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        return getUsers(stream).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        return getEventSet(getFilteredByName(user, stream)).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = stream.filter(entity -> entity.getIp().equals(ip));
        return getUsers(stream);
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, Event.LOGIN);
        return getUsers(stream);
    }

    private static Stream<LogEntity> getFilteredByEvent(Stream<LogEntity> stream, Event login) {
        return stream.filter(entity -> entity.getEvent() == login);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, Event.DOWNLOAD_PLUGIN);
        return getUsers(stream);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, Event.WRITE_MESSAGE);
        return getUsers(stream);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, Event.SOLVE_TASK);
        return getUsers(stream);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, Event.SOLVE_TASK);
        stream = stream.filter(entity -> entity.getTaskNumber() == task);
        return getUsers(stream);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, Event.DONE_TASK);
        return getUsers(stream);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, Event.DONE_TASK);
        stream = stream.filter(entity -> entity.getTaskNumber() == task);
        return getUsers(stream);
    }

    private static Set<Date> getDateSet(Stream<LogEntity> stream) {
        return stream.map(LogEntity::getDate).collect(Collectors.toSet());
    }

    private static Stream<LogEntity> getFilteredByName(String user, Stream<LogEntity> stream) {
        return stream.filter(entity -> entity.getUsername().equals(user));
    }

    private static Date getMinDate(Stream<LogEntity> stream) {
        Optional<LogEntity> min = stream.min(Comparator.comparing(LogEntity::getDate));
        return min.map(LogEntity::getDate).orElse(null);
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, event);
        stream = getFilteredByName(user, stream);
        return getDateSet(stream);
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = stream.filter(entity -> entity.getStatus() == Status.FAILED);
        return getDateSet(stream);
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = stream.filter(entity -> entity.getStatus() == Status.ERROR);
        return getDateSet(stream);
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByName(user, stream);
        stream = getFilteredByEvent(stream, Event.LOGIN);
        return getMinDate(stream);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByName(user, stream);
        stream = getFilteredByEvent(stream, Event.SOLVE_TASK);
        stream = stream.filter(entity -> entity.getTaskNumber() == task);
        return getMinDate(stream);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByName(user, stream);
        stream = getFilteredByEvent(stream, Event.DONE_TASK);
        stream = stream.filter(entity -> entity.getTaskNumber() == task);
        return getMinDate(stream);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByName(user, stream);
        stream = getFilteredByEvent(stream, Event.WRITE_MESSAGE);
        return getDateSet(stream);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByName(user, stream);
        stream = getFilteredByEvent(stream, Event.DOWNLOAD_PLUGIN);
        return getDateSet(stream);
    }

    private static Set<Event> getEventSet(Stream<LogEntity> stream) {
        return stream.map(LogEntity::getEvent).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        return stream.map(LogEntity::getEvent).collect(Collectors.toSet()).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        return getEventSet(stream);
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = stream.filter(entity -> entity.getIp().equals(ip));
        return getEventSet(stream);
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByName(user, stream);
        return getEventSet(stream);
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = stream.filter(entity -> entity.getStatus() == Status.FAILED);
        return getEventSet(stream);
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = stream.filter(entity -> entity.getStatus() == Status.ERROR);
        return getEventSet(stream);
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, Event.SOLVE_TASK);
        stream = stream.filter(entity -> entity.getTaskNumber() == task);
        return (int) stream.count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, Event.DONE_TASK);
        stream = stream.filter(entity -> entity.getTaskNumber() == task);
        return (int) stream.count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, Event.SOLVE_TASK);
        return getIntegerIntegerMap(stream);
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        stream = getFilteredByEvent(stream, Event.DONE_TASK);
        return getIntegerIntegerMap(stream);
    }

    private static Map<Integer, Integer> getIntegerIntegerMap(Stream<LogEntity> stream) {
        Map<Integer, Integer> map = new HashMap<>();
        stream.forEach(entity -> {
            if (map.containsKey(entity.getTaskNumber())) {
                map.put(entity.getTaskNumber(), map.get(entity.getTaskNumber()) + 1);
            } else {
                map.put(entity.getTaskNumber(), 1);
            }
        });
        return map;
    }

    @Override
    public Set<Object> execute(String query) {
        String[] split = query.split(" ");
        if (split.length == 2) {
            String command = split[0];
            String parameter = split[1];

            if (!command.equals("get")) {
                return null;
            }

            switch (parameter) {
                case "ip":
                    return getUniqueIPs(null, null).stream().map(x -> (Object) x).collect(Collectors.toSet());
                case "user":
                    return getAllUsers().stream().map(x -> (Object) x).collect(Collectors.toSet());
                case "date":
                    return getDateSet(getFilteredStream(null, null))
                            .stream().map(x -> (Object) x).collect(Collectors.toSet());
                case "event":
                    return getAllEvents(null, null).stream()
                            .map(x -> (Object) x).collect(Collectors.toSet());
                case "status":
                    return getStatusSet(null, null);
                default:
                    return null;
            }
        } else if (query.matches("get (\\w+) for (\\w+) = \"(.*?)\"")) {
            Stream<LogEntity> stream = getFilteredStream(null, null);

            String forParam = split[3];
            String param3 = query.substring(query.indexOf("\"") + 1, query.length() - 1);

            switch (forParam) {
                case "user":
                    String username = param3;
                    stream = stream.filter(entity -> entity.getUsername().equals(username));
                    break;
                case "date":
                    Date date = getDate(param3);
                    if (date == null) {
                        return null;
                    }
                    stream = stream.filter(entity -> entity.getDate().equals(date));
                    break;
                case "event":
                    Event event = Event.valueOf(param3);
                    stream = stream.filter(entity -> entity.getEvent().equals(event));
                    break;
                case "status":
                    Status status = Status.valueOf(param3);
                    stream = stream.filter(entity -> entity.getStatus().equals(status));
                    break;
                case "ip":
                    String ip = param3;
                    stream = stream.filter(entity -> entity.getIp().equals(ip));
                    break;
                default:
                    break;
            }

            String command = split[0];

            if ("get".equals(command)) {
                String getWhat = split[1];

                switch (getWhat) {
                    case "ip":
                        return getIpsSet(stream).stream().map(x -> (Object) x).collect(Collectors.toSet());
                    case "user":
                        return getUsers(stream).stream().map(x -> (Object) x).collect(Collectors.toSet());
                    case "date":
                        return getDateSet(stream).stream().map(x -> (Object) x).collect(Collectors.toSet());
                    case "event":
                        return getEventSet(stream).stream().map(x -> (Object) x).collect(Collectors.toSet());
                    case "status":
                        return stream.map(LogEntity::getStatus).collect(Collectors.toSet());
                    default:
                        return null;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    private static Date getDate(String param3) {
        String dateString = param3;
        String pattern = "dd.MM.yyyy HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    private Set<Object> getStatusSet(Date after, Date before) {
        Stream<LogEntity> stream = getFilteredStream(after, before);
        return stream.map(LogEntity::getStatus).collect(Collectors.toSet());
    }

}

class LogEntity {
    private String ip;
    private String username;
    private Date date;
    private Event event;
    private Integer taskNumber;
    private Status status;

    public LogEntity(String ip, String username, Date date, Event event, Integer taskNumber, Status status) {
        this.ip = ip;
        this.username = username;
        this.date = date;
        this.event = event;
        this.taskNumber = taskNumber;
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String toString() {
        return "Log{" +
                "ip='" + ip + '\'' +
                ", username='" + username + '\'' +
                ", date=" + date +
                ", event=" + event +
                ", taskNumber=" + taskNumber +
                ", status=" + status +
                '}';
    }
}
